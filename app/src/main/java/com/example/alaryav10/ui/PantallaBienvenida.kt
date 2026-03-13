package com.example.alaryav10.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.Nav_Inferior_Bienvenida
import com.example.alaryav10.ui.theme.BlueLight
import com.example.alaryav10.ui.theme.BluePrimary
import com.example.alaryav10.ui.theme.GrayText

@Composable
fun PantallaBienvenida(nav_controller: NavHostController) {
    val gradiente_suave = Brush.verticalGradient(
        colors = listOf(Color.White, BlueLight)
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradiente_suave)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoPrincipalApp()
            
            Spacer(Modifier.height(30.dp))
            
            Cabecera_Textos_Intro()
            
            Spacer(Modifier.height(60.dp))
            
            BotonLogueoGoogle { 
                nav_controller.navigate(Screen.GooglePicker.route) 
            }
            
            Spacer(Modifier.height(18.dp))
            
            NotaLegalPequena()
        }

        // Navegación de pie de página para cuando está afuera de la app principal
        Nav_Inferior_Bienvenida(nav_controller, Screen.Welcome.route)
    }
}

@Composable
private fun LogoPrincipalApp() {
    // el icono de la alarma metido en un cuadro blanco con bordes redondeados
    Box(
        modifier = Modifier
            .size(110.dp)
            .background(color = Color.White, shape = RoundedCornerShape(26.dp))
            .border(
                width = 1.dp, 
                color = Color.LightGray.copy(alpha = 0.2f), 
                shape = RoundedCornerShape(26.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Alarm,
            contentDescription = "Icono de AlarYa",
            modifier = Modifier.size(60.dp),
            tint = BluePrimary
        )
    }
}

@Composable
private fun Cabecera_Textos_Intro() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "AlarYa",
            style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = (-1.5).sp
            )
        )
        Text(
            text = "Gestión de alarmas simplificada",
            color = GrayText,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun BotonLogueoGoogle(on_click_google: () -> Unit) {
    // boton de google, en clase se dijo que para esas funciones solo era simularlo- no tiene ningina logica
    OutlinedButton(
        onClick = on_click_google,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black.copy(alpha = 0.7f)),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle, 
            contentDescription = null, 
            tint = Color(0xFF4285F4) // Azul oficial de Google
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Continuar con Google", 
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun NotaLegalPequena() {
    Text(
        text = "Al continuar, aceptas nuestros términos de servicio y política de privacidad.",
        fontSize = 11.sp,
        color = GrayText.copy(alpha = 0.6f),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 30.dp),
        lineHeight = 14.sp
    )
}
