package com.example.roomie.di.api

import com.example.roomie.data.repositories.PaymentRepository
import com.example.roomie.data.repositories.PersonRepository
import com.example.roomie.di.api.repositories.PaymentRepositoryImpl
import com.example.roomie.di.api.repositories.PersonRepositoryImpl
import com.example.roomie.domain.typeAdapters.LocalDateTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun providesPaymentRepository(
        gson: Gson
    ) : PaymentRepository {
        return PaymentRepositoryImpl(gson)
    }

    @Provides
    @Singleton
    fun providesPersonRepository(
        gson: Gson
    ) : PersonRepository {
        return PersonRepositoryImpl(gson)
    }
}