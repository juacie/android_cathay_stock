package com.juacie.cathay.stock.app.kit.function

import android.util.Log
import java.util.*

object LogObj {
    private const val appName = "CathayStock"
    private val counterList: MutableList<MethodCounter> = ArrayList()

    private class MethodCounter(var methodName: String, var counter: Int)

    /**
     * 追蹤log的內容
     * 格式為以下：
     * 1. [CathayStock] - 廠商名稱
     * 2. [main] - 執行緒
     * 3. [Mon 01 10 12:13:36 2022] - 編譯時間
     * 4. [class::method()] - 呼叫的位置
     * 5. [43] - 呼叫的程式碼行數
     * 6. [1] - 第幾次呼叫
     * 7. - (msg) - 客製化追蹤內容
     *
     * @param obj 輸入要顯示的log內容
     */
    fun trace(obj: Any) {
        Log.i("zxc", getMsg(obj))
    }

    private fun getMsg(obj: Any): String {
        val reference = getRefFunctionInfo()
        return "[$appName] [${Thread.currentThread().name}] ${getRefFunctionInfo()} [${
            getRefCounter(
                reference
            )
        }]\n - $obj"
    }

    private fun getRefFunctionInfo(): String {
        val elements = Thread.currentThread().stackTrace
        val element = elements[5]
        return "[${element.className}::${element.methodName}] [${element.lineNumber}]"
    }

    private fun getRefFunctionUpInfo(): String {
        val elements = Thread.currentThread().stackTrace
        val element = elements[5]
        return "[${element.className}::${element.methodName}] [${element.lineNumber}]"
    }

    private fun getRefCounter(reference: String): String {
        if (counterList.isNotEmpty()) {
            for (i in counterList.indices) {
                val method = counterList[i].methodName
                if (method == reference) {
                    counterList[i].counter++
                    return counterList[i].counter.toString()
                }
            }
        }
        counterList.add(MethodCounter(reference, 1))
        return "1"
    }
}
