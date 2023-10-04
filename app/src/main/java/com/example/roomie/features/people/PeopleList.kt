package com.example.roomie.features.people

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.roomie.domain.people.Person
import com.example.roomie.features.people.components.EditPerson
import com.example.roomie.features.people.components.PersonCard
import com.example.roomie.features.shared.Header

@Composable
fun PeopleList(
    people: List<Person>,
    setPeople: (List<Person>) -> Unit,
){
    var isEditPersonOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Header("Settings")

        Box(
            modifier = Modifier
                .padding(10.dp),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Text(
                        text = "People",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }

                items(people.count()) {index ->
                    PersonCard(
                        person = people[index],
                        onSave = {
                            val newList = people.toMutableList()
                            newList.removeAt(index)
                            newList.add(index, it)
                            setPeople(newList)
                        },
                        onDelete = { setPeople(people - it) },
                    )
                }

                item {
                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        onClick = { isEditPersonOpen = true }
                    )
                    {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Someone",
                            )
                            Text("Add Someone")
                        }
                    }
                }
            }
        }
    }

    if (isEditPersonOpen) {
        EditPerson(
            onClose = { isEditPersonOpen = false },
            onDelete = { },
            onSave = { setPeople(people + it) },
        )
    }
}