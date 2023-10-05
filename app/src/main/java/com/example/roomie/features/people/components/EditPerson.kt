package com.example.roomie.features.people.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.window.Dialog
import com.example.roomie.domain.people.Person
import com.example.roomie.features.people.PeopleHelper
import com.example.roomie.ui.theme.DarkGreen
import com.example.roomie.ui.theme.SubtleRed
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker

@Composable
fun EditPerson(
    person: Person? = null,
    onClose: () -> Unit,
    onDelete: (Person) -> Unit,
    onSave: (Person) -> Unit,
) {
    val item = person ?: Person()

    var name by remember { mutableStateOf(item.name) }
    var color by remember { mutableStateOf(item.color) }

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
                        text = "What's their name?",
                        fontWeight = FontWeight.Bold,
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0x3BA3A3A3)
                        ),
                    )
                }

                item {
                    Text(
                        text = "What's their favorite color?",
                        fontWeight = FontWeight.Bold,
                    )
                    HsvColorPicker(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .height(250.dp)
                            .align(Alignment.Center),
                        controller = ColorPickerController(),
                        initialColor = person?.color,
                        onColorChanged = {
                            color = it.color
                        }
                    )
                }

                items(error.count()) { index ->
                    Text(
                        text = error[index],
                        color = Color.Red,
                        fontSize = 12.sp,
                    )
                }

                item {
                    Row(
                        modifier = Modifier
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
                                val newPerson = Person(
                                    name = name,
                                    color = color,
                                )
                                error = PeopleHelper.verifyPerson(newPerson)
                                if (error.isEmpty()) {
                                    onSave(newPerson)
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