package com.example.roomie.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.roomie.domain.payments.Payment
import com.example.roomie.domain.people.PeopleHelper
import com.example.roomie.domain.people.Person
import com.example.roomie.features.monthlySummary.MonthlySummary
import com.example.roomie.features.settings.Settings

@Composable
fun HomeScreen(
    people: List<Person>,
    payments: List<Payment>,
) {

    Column (
        modifier = Modifier.fillMaxSize(),
    ) {

        MonthlySummary(
            people = people,
            payments = payments,
        )

        val peopleHelper = PeopleHelper(LocalContext.current)

        Settings(
            people = people,
            setPeople = { people ->
                peopleHelper.setPeople(people)
            }
        )
    }

//    Column {
//        Column {
//            Text(
//                text = "People",
//                fontWeight = FontWeight.Bold
//            )
//
//            LazyColumn {
//                items(people.size) { index ->
//                    Column {
//                        Text(
//                            text = people[index].name,
//                        )
//                        Text(
//                            text = people[index].color.toString(),
//                        )
//                    }
//                }
//            }
//        }
//
//        var personName by remember { mutableStateOf("") }
//
//        Row {
//            TextField(
//                value = personName,
//                onValueChange = { personName = it }
//            )
//
//            Button(
//                onClick = {
//                    peopleHelper.setPeople(
//                        people + Person(
//                            name = personName,
//                        )
//                    )
//
//                    people = peopleHelper.getPeople()
//                }
//            ) {
//                Text(
//                    text = "Add Person"
//                )
//            }
//        }
//    }
}
