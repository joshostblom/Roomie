package com.example.roomie.data.repositories

import com.example.roomie.data.models.Person

interface PersonRepository {
    suspend fun getPeople() : List<Person>
    suspend fun setPeople(people: List<Person>) : List<Person>
}