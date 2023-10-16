package com.leaf3stones.daysmaster.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leaf3stones.daysmaster.data.Event
import com.leaf3stones.daysmaster.util.DateFormat

@Composable
fun HomeScreen(
    onAddNewEventButtonClick: () -> Unit,
    onGoDetailButtonClicked: (eventId: Int) -> Unit
) {
    val viewModel: HomeScreenViewModel = viewModel()
    val topEvent: Event? by viewModel.topEvent.collectAsState(initial = null)
    val allEventsState by viewModel.allEvents.collectAsState(initial = emptyList())
    val headEvents by viewModel.headEvents.collectAsState(initial = emptyList())


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            TopEventItem(event = topEvent)
            Text(
                text = "所有事件:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                fontSize = 36.sp
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
            ) {
                items(headEvents.size) {
                    EventItem(event = headEvents[it], onEventItemClicked = onGoDetailButtonClicked)
                }

                items(allEventsState.size) {
                    EventItem(
                        event = allEventsState[it],
                        onEventItemClicked = onGoDetailButtonClicked
                    )
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = {
                onAddNewEventButtonClick()
            }, modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(bottom = 64.dp, end = 24.dp)
        ) {
            Text(text = "新事件", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun TopEventItem(event: Event?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (event != null) {
                Text(
                    text = event.eventName,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = DateFormat.getDateDiffString(event.date, event.addOneDay),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(0.dp)
                        .weight(0.2f)
                )
            }
        }
    }
}

@Composable
fun EventItem(event: Event, onEventItemClicked: (id: Int) -> Unit) {
    val contentColor = if (event.showAtTop) {
        Color.Red
    } else {
        Color.Black
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onEventItemClicked(event.eventId) },
        colors = CardDefaults.cardColors(
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .height(85.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = event.eventName,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = DateFormat.getDateDiffString(event.date, event.addOneDay),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.3f)
            )
        }
    }
}
