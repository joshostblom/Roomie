package com.example.roomie.di.person

import com.example.roomie.data.repositories.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersonModule {

    @Provides
    @Singleton
    fun providesGetPeopleUseCase(personRepository: PersonRepository): GetPeopleUseCase {
        return GetPeopleUseCase(personRepository)
    }

    @Provides
    @Singleton
    fun providesSetPeopleUseCase(personRepository: PersonRepository): SetPeopleUseCase {
        return SetPeopleUseCase(personRepository)
    }
}