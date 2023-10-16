package com.leaf3stones.daysmaster.detail


import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.leaf3stones.daysmaster.ComposeFileProvider
import com.leaf3stones.daysmaster.R
import com.leaf3stones.daysmaster.data.Event
import com.leaf3stones.daysmaster.data.EventRepository
import com.leaf3stones.daysmaster.util.DateFormat


@Composable
fun DetailScreen(eventId: Int, onGoBackButton: () -> Unit, onEditButtonClicked: () -> Unit) {
    val event = EventRepository().getEventById(eventId).collectAsState(initial = null)
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {

        var hasImage: Boolean by remember {
            mutableStateOf(false)
        }

        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        Image(
            painter = painterResource(id = R.drawable.default_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        if (hasImage && (imageUri != null)) {
            // 5
            AsyncImage(
                model = imageUri,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "Selected image",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onGoBackButton() }
                    .padding(16.dp))

            Image(painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onEditButtonClicked() }
                    .padding(16.dp))
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .width(320.dp)
        ) {
            EventDetail(event = event.value)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {


                val imagePicker = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { uri ->
                        hasImage = uri != null
                        imageUri = uri
                    })

                val cameraLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.TakePicture(),
                    onResult = { success ->
                        hasImage = success
                    })


                Image(painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        imagePicker.launch("image/*")
                    })
                Image(painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        val uri = ComposeFileProvider.getImageUri(context)
                        hasImage = false
                        imageUri = uri
                        cameraLauncher.launch(uri)
                    })
                Image(painter = painterResource(id = com.leaf3stones.daysmaster.R.drawable.ic_share),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SEND)
                        val shareBody = DateFormat.getDetailScreenText(event.value!!) + DateFormat.getDetailScreenDateFromToday(event.value!!)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_SUBJECT, "纪念日")
                        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
                        startActivity(context, Intent.createChooser(intent, "分享纪念日"), null)
                    })
            }
        }
    }
}

@Composable
fun EventDetail(event: Event?, modifier: Modifier = Modifier) {

    if (event == null) {
        return
    }

    Card(
        modifier = modifier.height(240.dp)

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Cyan)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    DateFormat.getDetailScreenText(event),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 24.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp)
        ) {
            Text(
                DateFormat.getDetailScreenDateFromToday(event),
                modifier = Modifier.align(Alignment.Center),
                fontSize = 64.sp
            )
        }
    }


}