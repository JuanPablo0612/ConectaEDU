package com.conectaedu.android.ui.studygroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.conectaedu.android.domain.model.Message

@Composable
fun ReceivedMessageCard(message: Message) {
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
        OutlinedCard {
            Text(text = message.text, fontSize = 16.sp, modifier = Modifier.padding(20.dp))
        }

        Spacer(modifier = Modifier.fillMaxWidth(0.1f))
    }
}