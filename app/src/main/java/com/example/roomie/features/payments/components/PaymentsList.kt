package com.example.roomie.features.payments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomie.domain.payments.Payment
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun PaymentsCard(
    item: Payment,
    onDelete: (item: Payment) -> Unit,
    onSave: (item: Payment) -> Unit,
) {

    var isEditOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0x3BA3A3A3))
            .clickable {
                isEditOpen = true
            }
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = item.whoPaid.name.first().toString(),
                    fontWeight = FontWeight.Bold,
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(0.5f),
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                )

                Text(
                    text = DateTimeFormatter.ofPattern("MMM. d, yyyy", Locale.US).format(item.date),
                    color = Color.Gray,
                )
            }

            Text(
                modifier = Modifier
                    .weight(0.2f),
                text = String.format("$%.2f", item.payment),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
            )
        }

        if (isEditOpen) {
            EditPayment(
                payment = item,
                onClose = { isEditOpen = false },
                onDelete = { onDelete(it) },
                onSave = { onSave(it) },
            )
        }
    }
}