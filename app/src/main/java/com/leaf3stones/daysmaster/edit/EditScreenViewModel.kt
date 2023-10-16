package com.leaf3stones.daysmaster.edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.leaf3stones.daysmaster.data.Event
import com.leaf3stones.daysmaster.data.EventRepository
import java.util.Date


class EditScreenViewModel(private val eventId: Int) : ViewModel() {

    private val eventRepository = EventRepository()


    var editableEvent: MutableState<Event>
    var currentEventName: MutableState<String>

    var shouldShowDatePicker = mutableStateOf(false)

    var successfulEdit = mutableStateOf(false)
    var successPrompt: MutableState<String?> = mutableStateOf(null)


    init {
        val initEvent = if (eventId == -1) {
            Event(0, "", Date())
        } else {
            eventRepository.getEventByIdBlocking(eventId)
        }
        editableEvent = mutableStateOf(initEvent)
        currentEventName = mutableStateOf(editableEvent.value.eventName)
    }

    fun onNewDateSelect(newDate: Date) {
        editableEvent.value = editableEvent.value.copy(date = newDate)
        shouldShowDatePicker.value = false
    }

    fun onEventNameChange(newName: String) {
        editableEvent.value = editableEvent.value.copy(eventName = newName)
    }

    fun onShowTopSwitchValueChange(newValue: Boolean) {
        editableEvent.value = editableEvent.value.copy(showAtTop = newValue)
    }

    fun onAddOneDayValueChange(newValue: Boolean) {
        editableEvent.value = editableEvent.value.copy(addOneDay = newValue)
    }

    fun trySave() {
        if (editableEvent.value.eventName.isNotEmpty()) {
            eventRepository.addEvent(editableEvent.value)
            successPrompt.value = "成功"
            successfulEdit.value = true
        } else {
            successPrompt.value = "请输入事件名称"
        }
    }
}