package com.juacie.cathay.stock.app.base

interface IViewModel<out ViewModel : BaseViewModel> {
    val viewModel: ViewModel
}