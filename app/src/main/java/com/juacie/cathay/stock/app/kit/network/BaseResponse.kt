package com.juacie.cathay.stock.app.kit.network

sealed class BaseResponse<out T> {

    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val error: ErrorEntity) : BaseResponse<Nothing>()
}