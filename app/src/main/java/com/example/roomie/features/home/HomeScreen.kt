package com.example.roomie.features.home

import androidx.compose.runtime.Composable
import com.example.roomie.domain.payments.Payment
import com.example.roomie.domain.people.Person
import com.example.roomie.features.amountOwed.AmountOwedList

@Composable
fun HomeScreen(
    people: List<Person>,
    payments: List<Payment>,
) {

    AmountOwedList(
        people = people,
        payments = payments,
    )

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
