package com.example.roomie.features.payments.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import com.example.roomie.data.models.Payment
import com.example.roomie.domain.people.PeopleDatabase
import com.example.roomie.features.payments.PaymentHelper
import com.example.roomie.ui.theme.DarkGreen
import com.example.roomie.ui.theme.SubtleRed
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPayment(
    payment: Payment? = null,
    onClose: () -> Unit,
    onDelete: (item: Payment) -> Unit,
    onSave: (item: Payment) -> Unit,
) {
    val people = PeopleDatabase(LocalContext.current).getPeople()
    val item = payment ?: Payment()

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
    var error by remember { mutableStateOf(emptyList<String>()) }

    Dialog(
        onDismissRequest = { onClose() },
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(color = MaterialTheme.colorScheme.background)
                .padding(25.dp),
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                item {
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
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0x3BA3A3A3)
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
                }

                item {
                    Text(
                        text = "Where was it paid?",
                        fontWeight = FontWeight.Bold,
                    )
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x3BA3A3A3)
                        ),
                    )
                }

                item {
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
                            text = DateTimeFormatter.ofPattern("MMM. d, yyyy", Locale.US)
                                .format(date),
                        )
                    }
                }

                item {
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
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x3BA3A3A3)
                        ),
                    )
                }

                items(error.count()) { index ->
                    Text(
                        text = error[index],
                        color = SubtleRed,
                        fontSize = 12.sp,
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(25),
                            colors = ButtonDefaults.buttonColors(containerColor = SubtleRed),
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
                            colors = ButtonDefaults.buttonColors(containerColor = DarkGreen),
                            onClick = {
                                val newPayment = Payment(
                                    title = title,
                                    whoPaid = selectedPerson,
                                    payment = amount.toDoubleOrNull() ?: 0.0,
                                    date = date,
                                )
                                error = PaymentHelper.verifyPayment(newPayment)
                                if (error.isEmpty()) {
                                    onSave(newPayment)
                                    onClose()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Save")
                        }
                    }
                }
            }
        }
    }
}