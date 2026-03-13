package com.example.alaryav10.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object GooglePicker : Screen("google_picker")
    object AlarmList : Screen("alarm_list")
    object Settings : Screen("settings")
    object HowItWorks : Screen("how_it_works")
    object CreateAlarm : Screen("create_alarm")
    object CreateAudio : Screen("create_audio")
    object ChooseChallenge : Screen("choose_challenge")
    object Success : Screen("success")
    object WebContext : Screen("web_context")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
        // daniel
        composable(Screen.Welcome.route) { PantallaBienvenida(navController) }
        composable(Screen.GooglePicker.route) { PantallaSelectorGoogle(navController) }
        composable(Screen.AlarmList.route) { PantallaListaAlarmas(navController) }
        composable(Screen.Settings.route) { PantallaAjustes(navController) }
        composable(Screen.HowItWorks.route) { PantallaComoFunciona(navController) }
        composable(Screen.CreateAlarm.route) { PantallaCrearAlarma(navController) }

        //carlos
        //composable(Screen.CreateAudio.route) { PantallaGrabarAudio(navController) }
        //composable(Screen.ChooseChallenge.route) { PantallaElegirDesafio(navController) }
        //composable(Screen.Success.route) { PantallaExito(navController) }
        //composable(Screen.WebContext.route) { PantallaContextoWeb(navController) }
    }
}
