package com.example.roomie.di.api.repositories

import android.content.Context
import com.example.roomie.Factory
import com.example.roomie.data.models.Person
import com.example.roomie.data.repositories.PersonRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val gson: Gson
) : PersonRepository {
    override suspend fun getPeople(): List<Person> {
        val sharedPref = Factory.shared().getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        val personType = object : TypeToken<List<Person>>() {}.type
        return gson.fromJson(sharedPref.getString("people", ""), personType) ?: emptyList()
    }

    override suspend fun setPeople(people: List<Person>) {
        val sharedPref = Factory.shared().getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPref.edit().putString("people", gson.toJson(people)).apply()
    }
}