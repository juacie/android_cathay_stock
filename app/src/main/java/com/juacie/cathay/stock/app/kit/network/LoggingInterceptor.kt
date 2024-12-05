package com.juacie.cathay.stock.app.kit.network

import com.juacie.cathay.stock.app.kit.function.LogObj
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log the request URL and headers
        val requestLog = StringBuilder("Sending request: ${request.url}\n${request.headers}\n")

        // Log the request body if it exists
        val requestBody = request.body
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            requestLog.append("Request body: ${buffer.readUtf8()}")
        }

        LogObj.trace(requestLog.toString())

        return chain.proceed(request)
    }
}
