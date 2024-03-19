package com.example.roomie.features.monthlySummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomie.data.models.Resource
import com.example.roomie.di.payment.GetPaymentsUseCase
import com.example.roomie.di.person.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MonthlySummaryViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MonthlySummaryState())
    val state: StateFlow<MonthlySummaryState> = _state.asStateFlow()

    init {
        getPeopleAndPayments()
    }

    private fun getPeopleAndPayments() {
        _state.value = combine(getPeopleUseCase(), getPaymentsUseCase()) { people, payments ->
            MonthlySummaryState(
                people = people.data ?: emptyList(),
                payments = payments.data ?: emptyList()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = MonthlySummaryState(isLoading = true)
        ).value

        getPeopleUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MonthlySummaryState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(people = result.data ?: emptyList())
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
                    _state.value = _state.value.copy(payments = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MonthlySummaryState(error = result.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}