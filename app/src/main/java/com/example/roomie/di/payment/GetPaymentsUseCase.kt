package com.example.roomie.di.payment

import com.example.roomie.data.models.Payment
import com.example.roomie.data.models.Resource
import com.example.roomie.data.repositories.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPaymentsUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
) {
    operator fun invoke (): Flow<Resource<List<Payment>>> =
        flow {
            emit(Resource.Loading())
            try {
                val payments = paymentRepository.getPayments()
                emit(Resource.Success(payments))
            }
            catch (ex: Exception) {
                emit(Resource.Error("Connection failed."))
            }
        }
}