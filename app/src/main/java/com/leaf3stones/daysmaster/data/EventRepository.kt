package com.leaf3stones.daysmaster.data

import com.leaf3stones.daysmaster.data.database.AppDatabaseProvider
import com.leaf3stones.daysmaster.data.database.EventDao

class EventRepository {

    private val eventDao: EventDao = AppDatabaseProvider.appDatabase!!.eventDao()

    fun getEventById(id: Int) = eventDao.getEventById(id)
    fun getEventByIdBlocking(id: Int) = eventDao.getEventByIdBlocking(id)

    fun getAllEvents() = eventDao.getAllShowEndEvents()
    fun getAllShowHeadEvents() = eventDao.getAllShowHeadEvents()


    fun updateEvent(event: Event) = eventDao.updateEvent(event)
    fun addEvent(event: Event) = eventDao.addEvent(event)

    fun getTopEvent() = eventDao.getTopEvent()
}