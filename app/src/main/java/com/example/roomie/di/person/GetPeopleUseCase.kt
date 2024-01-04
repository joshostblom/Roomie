package com.example.roomie.di.person

import com.example.roomie.data.models.Person
import com.example.roomie.data.models.Resource
import com.example.roomie.data.repositories.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    operator fun invoke (): Flow<Resource<List<Person>>> =
        flow {
            emit(Resource.Loading())
            try {
                val people = personRepository.getPeople()
                emit(Resource.Success(people))
            }
            catch (ex: Exception) {
                emit(Resource.Error("Connection failed."))
            }
        }
}