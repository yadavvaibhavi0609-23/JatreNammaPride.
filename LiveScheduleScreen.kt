package com.example.jatrenamma.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class JatreEvent(val time: String, val title: String, val description: String, val isOngoing: Boolean)

@Composable
fun LiveScheduleScreen(modifier: Modifier = Modifier) {
    val events = listOf(
        JatreEvent(time = "2:00 PM", title = "Temple Opening", description = "Opening ceremony and pooja", isOngoing = false),
        JatreEvent(time = "4:00 PM", title = "Rathotsava", description = "Main chariot procession starts from the temple", isOngoing = true),
        JatreEvent(time = "6:00 PM", title = "Wrestling Tournament", description = "Traditional wrestling matches at the ground", isOngoing = false),
        JatreEvent(time = "8:00 PM", title = "Drama Performance", description = "Mythological drama by local artists", isOngoing = false),
        JatreEvent(time = "10:00 PM", title = "Fireworks Display", description = "Grand finale fireworks", isOngoing = false),
    )

    var selectedEvent by remember { mutableStateOf<JatreEvent?>(null) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Live Event Schedule",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(events) { event ->
                EventCard(event = event) {
                    selectedEvent = event
                }
            }
        }
    }

    selectedEvent?.let { event ->
        AlertDialog(
            onDismissRequest = { selectedEvent = null },
            title = { Text(text = event.title, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(text = "Time: ${event.time}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = event.description)
                    if (event.isOngoing) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Status: Currently Live!", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedEvent = null }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun EventCard(event: JatreEvent, onClick: () -> Unit) {
    val cardColor = if (event.isOngoing) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (event.isOngoing) 8.dp else 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = event.time, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                if (event.isOngoing) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Badge(containerColor = MaterialTheme.colorScheme.error) {
                        Text("LIVE", color = Color.White, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(0.75f)) {
                Text(text = event.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = event.description, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
            }
        }
    }
}
