package com.example.roomie.features.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentsScreen(
    items: List<PaymentsItem>,
) {
    LazyColumn(
        modifier = Modifier
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(items.size) {
            PaymentsCard(item = items[it])
        }
    }
}

@Composable
fun PaymentsCard(
    item: PaymentsItem,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0x3BA3A3A3))
            .padding(10.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                modifier = Modifier
                    .weight(0.2f),
                text = "$" + item.payment.toString(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
            )

            Column (
                modifier = Modifier
                    .weight(0.5f),
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                )

                Text(
                    text = "",//android.text.format.DateFormat(),
                    color = Color.Gray,
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            Box(
                modifier = Modifier
                    .clickable { item.onEdit() }
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.primary),
            ) {
                Icon(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .padding(5.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White,
                )
            }

            Box(
                modifier = Modifier
                    .clickable { item.onEdit() }
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.Red),
            ) {
                Icon(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .padding(5.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                )
            }
        }
    }
}
