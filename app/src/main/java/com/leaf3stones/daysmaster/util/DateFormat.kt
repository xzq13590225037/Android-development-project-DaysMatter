package com.leaf3stones.daysmaster.util

import com.leaf3stones.daysmaster.data.Event
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class DateFormat {
    companion object {
        fun getDateDiffString(date: Date, shouldAddOneDayWhenInFeature: Boolean): String {
            val calendar = Calendar.getInstance()
            calendar.time = Date()

            val initYear = calendar[Calendar.YEAR]
            val initMonth = calendar[Calendar.MONTH]
            val initDay = calendar[Calendar.DAY_OF_MONTH]

            val now = GregorianCalendar(initYear, initMonth,initDay).time.time

            val diff = abs(date.time - now)

            val diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
            return when {
                diffDays == 0L -> "今天"
                date.time > now && shouldAddOneDayWhenInFeature -> "${diffDays + 1}天后"
                date.time > now -> "${diffDays}天后"
                else -> "${diffDays}天前"
            }

        }

        fun getDetailScreenText(event: Event):String {
            val calendar = Calendar.getInstance()
            calendar.time = Date()

            val initYear = calendar[Calendar.YEAR]
            val initMonth = calendar[Calendar.MONTH]
            val initDay = calendar[Calendar.DAY_OF_MONTH]

            val now = GregorianCalendar(initYear, initMonth,initDay).time.time

            val diff = abs(event.date.time - now)

            val diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

            return when {
                diffDays == 0L -> "距离 ${event.eventName} 还有"
                event.date.time > now && event.addOneDay -> "距离 ${event.eventName} 还有"
                event.date.time > now -> "距离 ${event.eventName} 还有"
                else -> "距离 ${event.eventName} 已经"
            }

        }

        fun getDetailScreenDateFromToday(event: Event):String {
            val calendar = Calendar.getInstance()
            calendar.time = Date()

            val initYear = calendar[Calendar.YEAR]
            val initMonth = calendar[Calendar.MONTH]
            val initDay = calendar[Calendar.DAY_OF_MONTH]

            val now = GregorianCalendar(initYear, initMonth,initDay).time.time

            val diff = abs(event.date.time - now)

            val diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

            return when {
                diffDays == 0L -> "0 天"
                event.date.time > now && event.addOneDay -> "${diffDays + 1} 天"
                else -> "$diffDays 天"
            }

        }

        fun getFormattedDate(date:Date) : String{
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            return simpleDateFormat.format(date)
        }

    }
}