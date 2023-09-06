package com.example.roomie.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.roomie.domain.people.PeopleHelper
import com.example.roomie.domain.people.Person

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val peopleHelper = PeopleHelper(LocalContext.current)
    var people by remember { mutableStateOf(peopleHelper.getPeople()) }


    Column {
        Column {
            Text(
                text = "People",
                fontWeight = FontWeight.Bold
            )

            LazyColumn {
                items(people.size) { index ->
                    Column {
                        Text(
                            text = people[index].name,
                        )
                        Text(
                            text = people[index].color.toString(),
                        )
                    }
                }
            }
        }

        var personName by remember { mutableStateOf("") }

        Row {
            TextField(
                value = personName,
                onValueChange = { personName = it }
            )

            Button(
                onClick = {
                    peopleHelper.setPeople(
                        people + Person(
                            name = personName,
                        )
                    )

                    people = peopleHelper.getPeople()
                }
            ) {
                Text(
                    text = "Add Person"
                )
            }
        }
    }
}
