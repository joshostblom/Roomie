package com.example.roomie.features.monthlySummary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomie.data.models.Resource
import com.example.roomie.di.payment.GetPaymentsUseCase
import com.example.roomie.di.person.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthlySummaryViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MonthlySummaryState())
    val state: State<MonthlySummaryState> = _state

    fun getPeopleAndPayments() {
        getPeopleUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MonthlySummaryState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MonthlySummaryState(
                        people = result.data ?: emptyList(),
                        payments = _state.value.payments,
                    )
                }

                is Resource.Error -> {
                    _state.value = MonthlySummaryState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)

        getPaymentsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MonthlySummaryState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MonthlySummaryState(
                        people = _state.value.people,
                        payments = result.data ?: emptyList(),
                    )
                }

                is Resource.Error -> {
                    _state.value = MonthlySummaryState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}