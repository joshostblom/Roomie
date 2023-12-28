package com.example.roomie.di.payment

import com.example.roomie.data.models.Payment
import com.example.roomie.data.models.Resource
import com.example.roomie.data.repositories.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetPaymentsUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    fun invoke (
        payments: List<Payment>
    ): Flow<Resource<Any?>> =
        flow {
            emit(Resource.Loading())
            try {
                val result = paymentRepository.setPayments(payments)
                emit(Resource.Success(result))
            }
            catch (ex: Exception) {
                emit(Resource.Error("Connection failed."))
            }
        }
}