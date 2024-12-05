package com.juacie.cathay.stock.app.kit.network

import com.juacie.cathay.stock.BuildConfig
import com.juacie.cathay.stock.app.kit.function.LogObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

object Client {
    val apiService: ApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                    .addHeader("Accept", "application/json, text/plain, */*")
                    .addHeader("Content-Type", "application/json")
                    .url(it.request().url)
                it.proceed(requestBuilder.build())
            }
            .addInterceptor(LoggingInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.TWSE_OPENAPI_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(ApiService::class.java)

    }

    suspend inline fun <reified T> safeApiCall(
        crossinline callFunction: suspend () -> Response<T>
    ): BaseResponse<T> = withContext(Dispatchers.IO) {
        try {
            val response = callFunction.invoke()

            if (response.isSuccessful) {
                val responseBody = response.body()
                // 根據返回的類型處理，避免錯誤的強制類型轉換
                return@withContext when {
                    responseBody != null -> BaseResponse.Success(responseBody)
                    T::class.java == Unit::class.java -> BaseResponse.Success(null as T)
                    else -> BaseResponse.Error(ErrorEntity.error(ErrorCode.CAST_ERROR))
                }
            } else {
                // 處理錯誤情況
                val errorBody = response.errorBody()?.string()
                val defaultCode = response.code()
                val defaultMessage = response.message()

                // 嘗試解析錯誤回應的 JSON 結構
                errorBody?.let {
                    try {
                        val errorEntity = parseErrorBody(it, defaultCode, defaultMessage)
                        return@withContext BaseResponse.Error(errorEntity)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        if (!it.contains("<html")) {
                            return@withContext BaseResponse.Error(
                                ErrorEntity(defaultCode, it)
                            )
                        }
                    }
                }

                // 返回解析錯誤的結果
                return@withContext BaseResponse.Error(
                    ErrorEntity(defaultCode, defaultMessage)
                )
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            return@withContext BaseResponse.Error(ErrorEntity.error(ErrorCode.CONNECTION_ERROR))
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext BaseResponse.Error(ErrorEntity.error(ErrorCode.UNKNOWN))
        }
    }

    @Throws(JSONException::class)
    fun parseErrorBody(
        errorBody: String, defaultCode: Int, defaultMessage: String
    ): ErrorEntity {
        LogObj.trace("errorBody = $errorBody")
        val jsonObject = JSONObject(errorBody)
        val statusCode = jsonObject.optInt("statusCode", defaultCode)
        val message = jsonObject.optJSONArray("message")?.let { jsonArray ->
            val messages = StringBuilder()
            for (i in 0 until jsonArray.length()) {
                if (i > 0) {
                    messages.append("\n")
                }
                messages.append(jsonArray.getString(i))
            }
            messages.toString()
        } ?: jsonObject.optString("message", defaultMessage)
        return ErrorEntity(statusCode, message)
    }
}