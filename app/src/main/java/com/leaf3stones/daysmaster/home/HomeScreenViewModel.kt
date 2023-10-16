package com.leaf3stones.daysmaster.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leaf3stones.daysmaster.data.Event
import com.leaf3stones.daysmaster.data.EventRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val eventRepository = EventRepository()

    var id = 0

    val allEvents = eventRepository.getAllEvents()
    val topEvent = eventRepository.getTopEvent()
    val headEvents = eventRepository.getAllShowHeadEvents()

    fun addEvent(event:Event) {
        eventRepository.addEvent(event)
    }
}