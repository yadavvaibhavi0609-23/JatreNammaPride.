package com.example.jatrenamma.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class LostItem(val id: Int, val name: String, val description: String, var isResolved: Boolean)

@Composable
fun LostAndFoundScreen(modifier: Modifier = Modifier) {
    var itemsList by remember {
        mutableStateOf(
            listOf(
                LostItem(1, "Lost Child", "6-year-old boy wearing red shirt near toy stalls.", false),
                LostItem(2, "Missing Keys", "Set of bike keys with a leather keychain.", false),
                LostItem(3, "Found Wallet", "Black leather wallet near the First-Aid post.", true)
            )
        )
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Open report dialog */ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, "Report Item")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            Text(
                text = "Lost & Found Board",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(itemsList, key = { it.id }) { item ->
                    LostItemCard(
                        item = item,
                        onResolve = { 
                            itemsList = itemsList.map { 
                                if (it.id == item.id) it.copy(isResolved = true) else it 
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LostItemCard(item: LostItem, onResolve: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isResolved) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (item.isResolved) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onErrorContainer
                )
                if (item.isResolved) {
                    Icon(Icons.Default.Check, contentDescription = "Resolved", tint = Color(0xFF4CAF50))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (item.isResolved) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onErrorContainer
            )
            
            if (!item.isResolved) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onResolve,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Mark as Resolved")
                }
            }
        }
    }
}
