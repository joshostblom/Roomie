package com.example.roomie.features.people.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomie.domain.people.Person

@Composable
fun PersonCard(
    person: Person,
    onSave: (Person) -> Unit,
    onDelete: (Person) -> Unit,
) {
    var isEditPersonOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = person.color)
            .clickable {
                isEditPersonOpen = true
            }
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = person.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

    if (isEditPersonOpen) {
        EditPerson(
            person = person,
            onClose = { isEditPersonOpen = false },
            onDelete = onDelete,
            onSave = onSave,
        )
    }
}