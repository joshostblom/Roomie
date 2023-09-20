package com.example.roomie.features.payments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomie.domain.payments.Payment
import com.example.roomie.features.payments.components.EditPayment
import com.example.roomie.features.payments.components.PaymentCard
import com.example.roomie.features.shared.Header

@Composable
fun PaymentsScreen(
    items: List<Payment>,
    setItems: (items: List<Payment>) -> Unit,
) {
    var isEditOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Header("Payments")

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(items.size) {
                    PaymentCard(
                        item = items[it],
                        onDelete = { item ->
                            setItems(items - item)
                            isEditOpen = false
                        },
                        onSave = { item ->
                            val newList = items.toMutableList()
                            newList.removeAt(it)
                            newList.add(it, item)
                            setItems(newList)
                            isEditOpen = false
                        }
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            shape = CircleShape,
            onClick = {
                isEditOpen = true
            })
        {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Payment"
                )
                Text(
                    text = "Add Payment",
                )
            }
        }
    }

    if (isEditOpen) {
        EditPayment(
            onClose = { isEditOpen = false },
            onDelete = { },
            onSave = { setItems(items + it) },
        )
    }
}

