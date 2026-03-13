package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.theme.GreenAccent

@Composable
fun PantallaExito(nav_controller: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAccent)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icono_Check_Grande()
        
        Spacer(Modifier.height(32.dp))
        
        Bloque_Mensajes_Finales()
        
        Spacer(Modifier.height(48.dp))
        
        Boton_Volver_A_Lista { 
            nav_controller.navigate(Screen.AlarmList.route) {
                // limpiamos para que no pueda volver a las pantallas de edición cuando da atras
                popUpTo(Screen.Welcome.route) { inclusive = false }
            }
        }
        
        Boton_Deshacer_Accion { nav_controller.popBackStack() }
    }
}

@Composable
private fun Icono_Check_Grande() {
    Surface(
        modifier = Modifier.size(110.dp), 
        shape = CircleShape, 
        color = Color.White,
        shadowElevation = 10.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Check, 
                contentDescription = "Check de éxito", 
                tint = GreenAccent, 
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Composable
private fun Bloque_Mensajes_Finales() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = Color.White.copy(alpha = 0.2f), 
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents, 
                    contentDescription = null, 
                    tint = Color.White, 
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "¡ÉXITO!",
                    color = Color.White, 
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp,
                    letterSpacing = 1.2.sp
                )
            }
        }
        
        Spacer(Modifier.height(24.dp))
        
        Text(
            text = "¡Alarma Lista!",
            fontSize = 34.sp, 
            fontWeight = FontWeight.Black, 
            color = Color.White
        )
        
        Text(
            text = "Sonará en 8h 15min",
            color = Color.White.copy(alpha = 0.85f),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun Boton_Volver_A_Lista(al_confirmar: () -> Unit) {
    Button(
        onClick = al_confirmar,
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = GreenAccent
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(
            text = "Ver mis alarmas", 
            fontWeight = FontWeight.Black,
            fontSize = 17.sp
        )
    }
}

@Composable
private fun Boton_Deshacer_Accion(al_cancelar: () -> Unit) {
    TextButton(
        onClick = al_cancelar,
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Text(
            text = "¿Cometiste un error? Deshacer",
            color = Color.White.copy(0.9f),
            textDecoration = TextDecoration.Underline,
            fontSize = 14.sp
        )
    }
}
