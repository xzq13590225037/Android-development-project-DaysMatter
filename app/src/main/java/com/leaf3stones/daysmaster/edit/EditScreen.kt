package com.leaf3stones.daysmaster.edit

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leaf3stones.daysmaster.util.DateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(eventId: Int, goBack: () -> Unit) {
    val viewModel: EditScreenViewModel = viewModel(factory = EditScreenViewModelFactory(eventId))

    val context = LocalContext.current

    DateSelector(shouldShowDate = viewModel.shouldShowDatePicker,
        initialDate = viewModel.editableEvent.value.date,
        onNewDateSelect = { viewModel.onNewDateSelect(it) })

    if (viewModel.successfulEdit.value) {
        goBack()
    }
    
    viewModel.successPrompt.value?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel.successPrompt.value = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {


        Text(text = "事件详情", fontSize = 36.sp, modifier = Modifier.padding(8.dp))

        TextField(
            value = viewModel.editableEvent.value.eventName,
            onValueChange = { viewModel.onEventNameChange(it)},
            label = { Text("事件名称") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    viewModel.shouldShowDatePicker.value = true
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "选择日期", fontSize = 24.sp
            )

            Text(text = DateFormat.getFormattedDate(viewModel.editableEvent.value.date))

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    viewModel.shouldShowDatePicker.value = true
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "顶置", fontSize = 24.sp
            )
            Switch(checked = viewModel.editableEvent.value.showAtTop,
                onCheckedChange = { newValue ->
                    viewModel.onShowTopSwitchValueChange(newValue)
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    viewModel.shouldShowDatePicker.value = true
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "正数日包含起始日（+1天）", fontSize = 20.sp
            )
            Switch(checked = viewModel.editableEvent.value.addOneDay,
                onCheckedChange = { newValue ->
                    viewModel.onAddOneDayValueChange(newValue)
                })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { viewModel.trySave() }) {
                Text(text = "保存", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
            }
        }

    }


}

@Composable
private fun DateSelector(
    shouldShowDate: MutableState<Boolean>,
    initialDate: Date,
    onNewDateSelect: (date: Date) -> Unit,
) {
    if (!shouldShowDate.value) {
        return
    }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.time = initialDate

    val initYear = calendar[Calendar.YEAR]
    val initMonth = calendar[Calendar.MONTH]
    val initDay = calendar[Calendar.DAY_OF_MONTH]


    val datePicker = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            onNewDateSelect(resultDate)
        }, initYear, initMonth, initDay
    )

    datePicker.show()

}