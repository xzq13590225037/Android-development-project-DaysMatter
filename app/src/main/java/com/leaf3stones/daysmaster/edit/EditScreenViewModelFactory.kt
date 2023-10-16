package com.leaf3stones.daysmaster.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class EditScreenViewModelFactory(private val eventId: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditScreenViewModel(eventId) as T
    }
}