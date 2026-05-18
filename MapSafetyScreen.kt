package com.example.jatrenamma.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jatrenamma.R

data class MapMarker(val name: String, val type: String, val icon: ImageVector, val color: Color)

@Composable
fun MapSafetyScreen(modifier: Modifier = Modifier) {
    val markers = listOf(
        MapMarker("First-Aid Post", "Emergency", Icons.Default.Info, Color(0xFFD32F2F)),
        MapMarker("North Parking Zone", "Parking", Icons.Default.LocationOn, Color(0xFF1976D2)),
        MapMarker("Sweet Stalls Lane", "Food", Icons.Default.ShoppingCart, Color(0xFFF57C00)),
        MapMarker("Main Temple Entrance", "Landmark", Icons.Default.LocationOn, Color(0xFF388E3C)),
    )

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Jatre Map & Safety",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Simulated Map Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Jatre Map",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Key Locations",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(markers) { marker ->
                MapMarkerCard(marker = marker)
            }
        }
    }
}

@Composable
fun MapMarkerCard(marker: MapMarker) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(marker.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(marker.icon, contentDescription = marker.name, tint = marker.color)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = marker.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = marker.type, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
