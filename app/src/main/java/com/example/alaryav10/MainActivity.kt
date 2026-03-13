package com.example.alaryav10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.alaryav10.ui.AppNavigation
import com.example.alaryav10.ui.theme.AlarYaV10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlarYaV10Theme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
