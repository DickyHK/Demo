package com.demo.currencylist.viewModel

import androidx.databinding.ObservableField

class CurrencyListItemViewModel {
    var name = ObservableField<String>("")
    var symbol = ObservableField<String>("")
    var icon = ObservableField<String>("")
}