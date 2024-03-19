package com.example.roomie.features.monthlySummary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomie.domain.calculations.CalculationsHelper
import com.example.roomie.data.models.Payment
import com.example.roomie.features.shared.Header
import com.example.roomie.ui.theme.BackgroundGrey
import com.example.roomie.ui.theme.DarkGreen
import com.example.roomie.ui.theme.SubtleRed
import java.time.LocalDate
import java.util.Locale

@Composable
fun MonthlySummary(
    modifier: Modifier = Modifier,
    viewModel: MonthlySummaryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val calculationsHelper = CalculationsHelper(
        people = state.people,
        payments = state.payments,
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Header("Monthly Summary")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color = BackgroundGrey)
                .padding(10.dp),
        ) {
            LazyColumn {
                items(state.people.size) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = state.people[it].color)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = state.people[it].name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                    state.payments.filter { payment ->
                        payment.whoPaid == state.people[it] && payment.date.monthValue == LocalDate.now().monthValue
                    }.forEach { payment ->
                        PaymentListItem(payment = payment)
                    }

                    HorizontalDivider(color = Color.Black)

                    PaymentListItem(
                        amount = calculationsHelper.getTotalSpent(
                            person = state.people[it],
                        ),
                        title = "Total Spent",
                    )

                    Spacer(modifier = Modifier.padding(10.dp))
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .background(color = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "Total",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    PaymentListItem(
                        amount = calculationsHelper.getTotalSpent(
                            month = LocalDate.now(),
                        ),
                        title = "Total Spent",
                    )
                }

                items(state.people.size) {

                    val totalOwed = calculationsHelper.getAmountOwed(
                        person = state.people[it],
                    )
                    PaymentListItem(
                        amount = totalOwed,
                        title = state.people[it].name + " Owes",
                        color = if (totalOwed > 0) SubtleRed else DarkGreen
                    )
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun PaymentListItem(
    payment: Payment? = null,
    amount: Double? = null,
    title: String? = null,
    color: Color? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart),
            text = title ?: payment?.title!!,
        )

        if (color != null) {
            Box(
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .clip(RoundedCornerShape(25))
                    .background(color = color)
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 5.dp),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    text = String.format(Locale.US, "$%.2f", amount ?: payment?.payment),
                )
            }
        } else {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = String.format(Locale.US, "$%.2f", amount ?: payment?.payment),
            )
        }
    }
}