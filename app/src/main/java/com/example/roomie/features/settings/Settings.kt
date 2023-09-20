package com.example.roomie.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomie.domain.people.Person
import com.example.roomie.features.shared.Header
import com.example.roomie.ui.theme.BackgroundGrey

@Composable
fun Settings(
    people: List<Person>,
    setPeople: (List<Person>) -> Unit,
) {

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

                items(people.count()) {
                    PersonCard(
                        person = people[it],
                        onSave = { setPeople(people + it) },
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

@Composable
fun PersonCard(
    person: Person,
    onSave: (Person) -> Unit,
    onDelete: (Person) -> Unit,
) {
    var isEditPersonOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = person.color)
            .clickable {
                isEditPersonOpen = true
            }
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = person.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

    if (isEditPersonOpen) {
        EditPerson(
            onClose = { isEditPersonOpen = false },
            onDelete = onDelete,
            onSave = onSave,
        )
    }
}