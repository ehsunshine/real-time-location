package com.example.realtimelocation.ui.activity

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.realtimelocation.data.tabs.BottomTabs
import com.example.realtimelocation.ui.home.HomeTabScreen
import com.example.realtimelocation.ui.map.MapTabScreen
import com.example.realtimelocation.ui.theme.RealtimeLocationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(SystemBarStyle.dark(Color.parseColor("#801b1b1b")))
        setContent {
            RealtimeLocationTheme {
                val navController = rememberNavController()
                val viewModel: MainActivityViewModel = koinViewModel()
                val tabs by viewModel.tabs.collectAsState()

                Scaffold(
                    bottomBar = {
                        BottomTabsNavigation(tabs, navController)
                    }
                ) { paddingValues ->
                    AppContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        navController,
                    )
                }
            }
        }
    }

    @Composable
    private fun BottomTabsNavigation(
        tabs: List<BottomTabs>,
        navController: NavHostController,
    ) {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            tabs.forEach { tab ->
                NavigationBarItem(
                    icon = {
                        Icon(imageVector = tab.icon, contentDescription = tab.title)
                    },
                    label = {
                        Text(text = tab.title)
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                    onClick = {
                        navController.navigate(tab.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = BottomTabs.Home.route,
    ) {
        composable(BottomTabs.Home.route) {
            HomeTabScreen(koinViewModel())
        }
        composable(BottomTabs.Map.route) {
            MapTabScreen(koinViewModel())
        }
    }
}
