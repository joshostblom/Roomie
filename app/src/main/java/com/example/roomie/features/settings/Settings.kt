package com.example.roomie.features.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.roomie.data.models.Person
import com.example.roomie.features.people.PeopleList

@Composable
fun Settings(
    people: List<Person>,
    setPeople: (List<Person>) -> Unit,
    rentCost: Double,
    setRentCost: (Double) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        PeopleList(
            people = people,
            setPeople = setPeople,
        )

        var rent by remember {
            mutableStateOf(
                if (rentCost == 0.0) {
                    ""
                } else {
                    rentCost.toString()
                }
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {

            Text(
                text = "Rent Cost",
                fontWeight = FontWeight.Bold,
            )

            Row {
                TextField(
                    value = rent,
                    onValueChange = {
                        if ("[0-9]*.?[0-9]?[0-9]?$".toRegex().matches(it)) {
                            rent = it
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

                Button(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    onClick = {
                        setRentCost(rent.toDoubleOrNull() ?: 0.0)
                    }
                ) {
                    Text(
                        text = "Apply"
                    )
                }
            }
        }
    }
}