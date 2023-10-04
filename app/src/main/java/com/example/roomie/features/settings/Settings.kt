package com.example.roomie.features.settings

import androidx.compose.runtime.Composable
import com.example.roomie.domain.people.Person
import com.example.roomie.features.people.PeopleList

@Composable
fun Settings(
    people: List<Person>,
    setPeople: (List<Person>) -> Unit,
) {
    PeopleList(
        people = people,
        setPeople = setPeople,
    )
}