package com.example.roomie.domain.people

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PeopleHelper(
    private val context: Context,
) {
    fun getPeople(): List<Person> {
        val sharedPref = context.getSharedPreferences("data", MODE_PRIVATE)
        val personType = object : TypeToken<List<Person>>() {}.type
        return Gson().fromJson(sharedPref.getString("people", ""), personType) ?: emptyList()
    }

    fun setPeople(
        people: List<Person>,
    ) {
        val sharedPref = context.getSharedPreferences("data", MODE_PRIVATE)
        sharedPref.edit().putString("people", Gson().toJson(people)).apply()
    }
}