package com.example.jatrenamma.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class CulturalStory(val title: String, val summary: String)

@Composable
fun CulturalStoriesScreen(modifier: Modifier = Modifier) {
    val stories = listOf(
        CulturalStory("The Legend of the Deity", "The village deity is believed to have saved the ancestors from a great famine centuries ago. The Rathotsava is celebrated as a mark of gratitude."),
        CulturalStory("Wrestling Traditions", "The wrestling tournament has a history of 150 years, starting as a friendly competition among local farmers to show their strength post-harvest."),
        CulturalStory("The Mythological Dramas", "The plays performed at night are based on local folklore passed down orally through generations, keeping the ancient stories alive.")
    )

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Cultural Stories & Legends",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(stories) { story ->
                StoryCard(story = story)
            }
        }
    }
}

@Composable
fun StoryCard(story: CulturalStory) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Placeholder for an image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = story.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = story.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Justify
            )
        }
    }
}
