package com.juacie.cathay.stock.app.kit.network

data class ErrorEntity(val code: Int, var message: String) {
    companion object {
        fun error(errorCode: ErrorCode): ErrorEntity {
            return ErrorEntity(errorCode.code, errorCode.message)
        }
    }
}

enum class ErrorCode(val code: Int, val message: String) {
    PARSE_ERROR(994, "Network json format got an error."),
    CONNECTION_ERROR(995, "A network error occurred, please check the network connection."),
    CERTIFICATE_ERROR(996, "Certificate error."),
    CONTEXT_ERROR(997, "Context error."),
    CAST_ERROR(998, "Content casting error."),
    UNKNOWN(999, "Unexpected error, please try again later."),
}