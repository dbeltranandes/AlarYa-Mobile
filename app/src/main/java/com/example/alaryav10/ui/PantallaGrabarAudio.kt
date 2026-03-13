package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.Boton_Volver_Atras
import com.example.alaryav10.ui.theme.BluePrimary
import com.example.alaryav10.ui.theme.BlueSecondary
import com.example.alaryav10.ui.theme.GreenAccent
import com.example.alaryav10.ui.theme.OrangeAccent

@Composable
fun PantallaGrabarAudio(controlador_nav: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePrimary)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Encabezado_Grabacion { controlador_nav.popBackStack() }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Zona_Visual_Microfono()
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Tarjeta_Contador_Tiempo(crono_texto = "00:04")
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Boton_Detener_Grabacion {
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        Boton_Guardar_Y_Seguir { 
            nav_to_exito(controlador_nav)
        }
    }
}

private fun nav_to_exito(nav: NavHostController) {
    nav.navigate(Screen.Success.route)
}

@Composable
private fun Encabezado_Grabacion(al_ir_atras: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(), 
        verticalAlignment = Alignment.CenterVertically
    ) {
        Boton_Volver_Atras(al_ir_atras)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Crear Audio",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
private fun Zona_Visual_Microfono() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            color = Color.White.copy(alpha = 0.12f),
            shape = CircleShape,
            modifier = Modifier.size(100.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Mic, 
                    contentDescription = null, 
                    tint = GreenAccent, 
                    modifier = Modifier.size(56.dp)
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Grabar nota de voz",
            fontSize = 20.sp, 
            fontWeight = FontWeight.Bold, 
            color = Color.White
        )
        Text(
            text = "Graba una frase que se reproducirá con tu alarma",
            color = Color.White.copy(alpha = 0.75f),
            fontSize = 14.sp
        )
    }
}

@Composable
private fun Tarjeta_Contador_Tiempo(crono_texto: String) {
    // Tarjeta que muestra el tiempo transcurrido de la grabación
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = crono_texto, 
                    fontSize = 52.sp, 
                    fontWeight = FontWeight.Black, 
                    color = BlueSecondary,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "GRABANDO...", 
                    color = Color.Red.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun Boton_Detener_Grabacion(on_stop: () -> Unit) {
    Surface(
        color = OrangeAccent, 
        shape = CircleShape, 
        modifier = Modifier
            .size(85.dp)
            .clickable { on_stop() },
        shadowElevation = 6.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Stop,
                contentDescription = "Detener",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
private fun Boton_Guardar_Y_Seguir(accion_save: () -> Unit) {
    Button(
        onClick = accion_save, 
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp), 
        colors = ButtonDefaults.buttonColors(containerColor = GreenAccent),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "Guardar Audio",
            fontWeight = FontWeight.Black,
            fontSize = 18.sp
        )
    }
}
