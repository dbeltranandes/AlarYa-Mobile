package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.Boton_Volver_Atras
import com.example.alaryav10.ui.components.Fila_AjusteManual
import com.example.alaryav10.ui.components.Opcion_Con_Switch_Manual
import com.example.alaryav10.ui.theme.*

private val separacion_entre_bloques = 20.dp
private val curvatura_tarjetas = 24.dp

@Composable
fun PantallaCrearAlarma(controlador_nav: NavHostController) {
    var posponer_activado by remember { mutableStateOf(true) }
    var modo_repeticion by remember { mutableStateOf("Una vez") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePrimary)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(separacion_entre_bloques)
    ) {
        Encabezado_Nueva_Alarma { controlador_nav.popBackStack() }
        
        Selector_De_Hora_Visual(hora_p = "07", minuto_p = "30", periodo_p = "AM")
        
        //opciones
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Opcion_Con_Switch_Manual(
                icono_opc = Icons.Default.Notifications, 
                texto_etiqueta = "Posponer (5 min)", 
                valor_arranque = posponer_activado,
                al_cambiar = { posponer_activado = it }
            )
            
            Fila_AjusteManual(
                icono_ajuste = Icons.Default.Repeat, 
                texto_mostrar = "Repetir: $modo_repeticion", 
                con_flechita = true,
                accion_click = { /* Abrir selector de repetición */ }
            )
        }
        
        Tarjeta_Contexto_Web(controlador_nav)
        
        Spacer(Modifier.weight(1f))
        
        Boton_Ir_A_Desafio { controlador_nav.navigate(Screen.ChooseChallenge.route) }
    }
}

@Composable
private fun Encabezado_Nueva_Alarma(al_volver: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Boton_Volver_Atras(al_volver)
        Spacer(Modifier.width(16.dp))
        Text(
            text = "Nueva Alarma",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
private fun Selector_De_Hora_Visual(hora_p: String, minuto_p: String, periodo_p: String) {
    // tarjeta de elegir hora, no funciona para mover, revisar si alcanzamos a implementarlo antes del viernes
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(curvatura_tarjetas),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Bloque_Tiempo_Estilizado(hora_p, BluePrimary)
                Text(
                    text = ":", 
                    fontSize = 50.sp, 
                    fontWeight = FontWeight.Bold, 
                    color = BluePrimary,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Bloque_Tiempo_Estilizado(minuto_p, BlueSecondary)
            }
            
            // el circulo de am pm arriba a la derecha
            Surface(
                color = OrangeAccent,
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = periodo_p, color = Color.White, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Composable
private fun Bloque_Tiempo_Estilizado(valor_t: String, color_t: Color) {
    Surface(
        color = BlueLight,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = valor_t,
            fontSize = 64.sp,
            fontWeight = FontWeight.Black,
            color = color_t,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun Tarjeta_Contexto_Web(nav: NavHostController) {
    var activado_contexto by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { nav.navigate(Screen.WebContext.route) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = OrangeAccent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Activar Contexto",
                    fontWeight = FontWeight.ExtraBold, 
                    color = Color.White,
                    fontSize = 18.sp
                )
                Text(
                    text = "Evita sonar en festivos sincronizando con el calendario web.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp
                )
            }
            Switch(
                checked = activado_contexto,
                onCheckedChange = { activado_contexto = it },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color.White,
                    checkedThumbColor = OrangeAccent,
                    uncheckedTrackColor = Color.Black.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Composable
private fun Boton_Ir_A_Desafio(al_click: () -> Unit) {
    Button(
        onClick = al_click,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BlueSecondary),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "Configurar Desafío", 
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(Modifier.width(8.dp))
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
    }
}
