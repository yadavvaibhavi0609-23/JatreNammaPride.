package com.example.jatrenamma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jatrenamma.ui.screens.CulturalStoriesScreen
import com.example.jatrenamma.ui.screens.LiveScheduleScreen
import com.example.jatrenamma.ui.screens.LostAndFoundScreen
import com.example.jatrenamma.ui.screens.MapSafetyScreen
import com.example.jatrenamma.ui.theme.JatreNammaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatreNammaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JatreApp()
                }
            }
        }
    }
}

sealed class BottomNavItem(val title: String, val icon: ImageVector) {
    object Schedule : BottomNavItem("Schedule", Icons.Default.DateRange)
    object LostFound : BottomNavItem("Lost & Found", Icons.Default.Search)
    object Map : BottomNavItem("Map", Icons.Default.LocationOn)
    object Stories : BottomNavItem("Stories", Icons.Default.Info)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JatreApp() {
    var currentScreen by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Schedule) }
    val items = listOf(
        BottomNavItem.Schedule,
        BottomNavItem.LostFound,
        BottomNavItem.Map,
        BottomNavItem.Stories
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.jatreapp_logo),
                        contentDescription = "Jatre Namma Logo",
                        modifier = Modifier.height(40.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentScreen == item,
                        onClick = { currentScreen = item }
                    )
                }
            }
        }
    ) { innerPadding ->
        Modifier.padding(innerPadding).let {
            when (currentScreen) {
                BottomNavItem.Schedule -> LiveScheduleScreen(modifier = Modifier.padding(innerPadding))
                BottomNavItem.LostFound -> LostAndFoundScreen(modifier = Modifier.padding(innerPadding))
                BottomNavItem.Map -> MapSafetyScreen(modifier = Modifier.padding(innerPadding))
                BottomNavItem.Stories -> CulturalStoriesScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JatreAppPreview() {
    JatreNammaTheme {
        JatreApp()
    }
}
