package com.example.roomie.features.people

import com.example.roomie.data.models.Person

class PeopleHelper {
    companion object {
        fun verifyPerson(
            person: Person,
        ): List<String> {
            val result = mutableListOf<String>()

            if (person.name.isBlank()) {
                result.add("Name must be specified.")
            }

            return result
        }
    }
}