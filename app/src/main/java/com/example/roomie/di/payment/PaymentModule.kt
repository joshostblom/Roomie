package com.example.roomie.di.payment

import com.example.roomie.data.repositories.PaymentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaymentModule {

    @Provides
    @Singleton
    fun providesGetPaymentsUseCase(paymentRepository: PaymentRepository): GetPaymentsUseCase {
        return GetPaymentsUseCase(paymentRepository)
    }

    @Provides
    @Singleton
    fun providesSetPaymentsUseCase(paymentRepository: PaymentRepository): SetPaymentsUseCase {
        return SetPaymentsUseCase(paymentRepository)
    }
}