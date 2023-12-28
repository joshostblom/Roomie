package com.example.roomie.di.person

import com.example.roomie.data.models.Person
import com.example.roomie.data.models.Resource
import com.example.roomie.data.repositories.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetPeopleUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    fun invoke (
        people: List<Person>
    ): Flow<Resource<Any?>> =
        flow {
            emit(Resource.Loading())
            try {
                val result = personRepository.setPeople(people)
                emit(Resource.Success(result))
            }
            catch (ex: Exception) {
                emit(Resource.Error("Connection failed."))
            }
        }
}