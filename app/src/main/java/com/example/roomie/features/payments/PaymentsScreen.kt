package com.example.roomie.features.payments

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.roomie.domain.payments.PaymentsItem
import com.example.roomie.domain.people.PeopleHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun PaymentsScreen(
    items: List<PaymentsItem>,
    setItems: (items: List<PaymentsItem>) -> Unit,
) {
    var isEditOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(items.size) {
                PaymentsCard(
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
            onDelete = {},
            onSave = { setItems(items + it) }
        )
    }
}

@Composable
fun PaymentsCard(
    item: PaymentsItem,
    onDelete: (item: PaymentsItem) -> Unit,
    onSave: (item: PaymentsItem) -> Unit,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPayment(
    payment: PaymentsItem? = null,
    onClose: () -> Unit,
    onDelete: (item: PaymentsItem) -> Unit,
    onSave: (item: PaymentsItem) -> Unit,
) {
    val people = PeopleHelper(LocalContext.current).getPeople()
    val item = payment ?: PaymentsItem()

    var selectedPerson by remember { mutableStateOf(item.whoPaid) }
    var title by remember { mutableStateOf(item.title) }
    var date by remember { mutableStateOf(item.date) }
    var amount by remember {
        mutableStateOf(
            if (item.payment == 0.0) {
                ""
            } else {
                item.payment.toString()
            }
        )
    }

    Dialog(
        onDismissRequest = { onClose() },
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(color = MaterialTheme.colorScheme.background)
                .size(350.dp, 500.dp)
                .padding(25.dp),
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Text(
                    text = "Who's paying?",
                    fontWeight = FontWeight.Bold,
                )

                var isExpanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded }) {

                    TextField(
                        value = selectedPerson.name,
                        modifier = Modifier.menuAnchor(),
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(
                                0x3BA3A3A3
                            )
                        ),
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false })
                    {
                        people.forEach { person ->
                            DropdownMenuItem(
                                text = { Text(person.name) },
                                onClick = {
                                    selectedPerson = person
                                    isExpanded = false
                                })
                        }
                    }
                }

                Text(
                    text = "Where was it paid?",
                    fontWeight = FontWeight.Bold,
                )
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(
                            0x3BA3A3A3
                        )
                    ),
                )

                val datePicker = DatePickerDialog(LocalContext.current)
                datePicker.updateDate(date.year, date.monthValue - 1, date.dayOfMonth)
                datePicker.datePicker.setOnDateChangedListener { _, year, month, day ->
                    date = LocalDate.of(year, month + 1, day)
                }
                Text(
                    text = "When was it paid?",
                    fontWeight = FontWeight.Bold,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(color = Color(0x3BA3A3A3))
                        .padding(15.dp)
                        .clickable { datePicker.show() }
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart),
                        text = DateTimeFormatter.ofPattern("MMM. d, yyyy", Locale.US).format(date),
                    )
                }

                Text(
                    text = "How much was paid?",
                    fontWeight = FontWeight.Bold,
                )
                TextField(
                    value = amount,
                    onValueChange = {
                        if ("[0-9]*.?[0-9]?[0-9]?$".toRegex().matches(it)) {
                            amount = it
                        }
                    },
                    placeholder = { Text("0.00") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(
                            0x3BA3A3A3
                        )
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = {
                        onDelete(item)
                        onClose()
                    }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }

                Button(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF07D100)),
                    onClick = {
                        onSave(
                            PaymentsItem(
                                title = title,
                                whoPaid = selectedPerson,
                                payment = amount.toDouble(),
                                date = date,
                            )
                        )
                        onClose()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "Save")
                }
            }
        }
    }
}