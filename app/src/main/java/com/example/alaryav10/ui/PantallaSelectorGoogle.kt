package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.Fila_Para_Elegir_Cuenta
import com.example.alaryav10.ui.theme.GrayText

@Composable
fun PantallaSelectorGoogle(nav_controller: NavHostController) {
    // Un fondo oscuro semitransparente para que parezca un modal encima de la pantalla de bienvenida
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.55f)),
        contentAlignment = Alignment.Center
    ) {
        Dialogo_Selector_Cuentas(nav_controller)
    }
}

@Composable
private fun Dialogo_Selector_Cuentas(nav: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f) // Que no ocupe todo el ancho
            .padding(16.dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Cabecera_Marca_Google()
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Textos_Del_Selector()
            
            Spacer(modifier = Modifier.height(24.dp))

            Listado_De_Cuentas_Mock(nav)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Aviso_Privacidad_Chico()
            
            Spacer(modifier = Modifier.height(28.dp))
            
            Acciones_Del_Dialogo(nav)
        }
    }
}

@Composable
private fun Cabecera_Marca_Google() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.AccountCircle, 
            contentDescription = "Logo Google", 
            tint = Color(0xFF4285F4)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Google", 
            fontWeight = FontWeight.SemiBold, 
            fontSize = 17.sp,
            color = Color.Black.copy(alpha = 0.65f)
        )
    }
}

@Composable
private fun Textos_Del_Selector() {
    Column {
        Text(
            text = "Elige una cuenta", 
            style = MaterialTheme.typography.headlineSmall, 
            fontWeight = FontWeight.Black
        )
        Text(
            text = "para continuar en AlarYa", 
            color = GrayText,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun Listado_De_Cuentas_Mock(controlador: NavHostController) {
    // Función de ayuda para navegar después de elegir
    val al_elegir_cuenta = { 
        controlador.navigate(Screen.AlarmList.route) {
            popUpTo(Screen.Welcome.route) { inclusive = true }
        } 
    }
    
    Column {
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.25f))
        
        Fila_Para_Elegir_Cuenta(
            nombre_u = "Juan Pérez", 
            email_u = "juan.perez@gmail.com", 
            letra_p = "J", 
            al_clic = al_elegir_cuenta
        )
        
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.25f))
        
        Fila_Para_Elegir_Cuenta(
            nombre_u = "Maria Rodriguez", 
            email_u = "maria.rodz@gmail.com", 
            letra_p = "M", 
            al_clic = al_elegir_cuenta
        )
        
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.25f))

        Fila_Para_Elegir_Cuenta(
            nombre_u = "Usar otra cuenta", 
            email_u = "", 
            letra_p = null, 
            al_clic = al_elegir_cuenta
        )
        
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.25f))
    }
}

@Composable
private fun Aviso_Privacidad_Chico() {
    Text(
        text = "Para continuar, Google compartirá tu nombre, dirección de correo electrónico y foto de perfil con AlarYa. Antes de usar esta app, puedes revisar su política de privacidad.",
        fontSize = 11.sp,
        color = GrayText.copy(alpha = 0.75f),
        lineHeight = 15.sp
    )
}

@Composable
private fun Acciones_Del_Dialogo(nav_c: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(), 
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = { nav_c.popBackStack() },
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Text(text = "Cancelar", color = Color(0xFF1A73E8), fontWeight = FontWeight.Bold)
        }
    }
}
