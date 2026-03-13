package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.MenuPrincipal_AlarYa
import com.example.alaryav10.ui.components.Fila_AjusteManual
import com.example.alaryav10.ui.components.Opcion_Con_Switch_Manual
import com.example.alaryav10.ui.theme.BluePrimary
import com.example.alaryav10.ui.theme.OrangeAccent

@Composable
fun PantallaAjustes(nav_controller: NavHostController) {
    Scaffold(
        bottomBar = { MenuPrincipal_AlarYa(nav_controller, Screen.Settings.route) }
    ) { rellenos_v ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BluePrimary)
                .padding(rellenos_v)
                .padding(horizontal = 24.dp, vertical = 30.dp)
        ) {
            Text(
                text = "Ajustes",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Black
            )
            
            Spacer(Modifier.height(24.dp))

            // aca se meten todas las filas de las opciones
            Bloque_Opciones_Ajustes()
            
            Spacer(Modifier.weight(1f))

            Tarjeta_Tip_Informativo()
        }
    }
}

@Composable
private fun Bloque_Opciones_Ajustes() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Fila_AjusteManual(
            icono_ajuste = Icons.Default.Person, 
            texto_mostrar = "Usuario: User", 
            con_flechita = true
        )
        Opcion_Con_Switch_Manual(
            icono_opc = Icons.Default.Palette, 
            texto_etiqueta = "Tema oscuro",
            valor_arranque = true
        )
        Opcion_Con_Switch_Manual(
            icono_opc = Icons.Default.VolumeUp, 
            texto_etiqueta = "Volumen máximo",
            valor_arranque = true
        )
        Opcion_Con_Switch_Manual(
            icono_opc = Icons.Default.Vibration, 
            texto_etiqueta = "Alarma predeterminada",
            valor_arranque = true
        )
        
        Spacer(Modifier.height(8.dp))
        
        Fila_AjusteManual(
            icono_ajuste = Icons.Default.Info, 
            texto_mostrar = "Versión 1.0 - ALARYA", 
            con_flechita = false
        )
    }
}

@Composable
private fun Tarjeta_Tip_Informativo() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(containerColor = OrangeAccent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(46.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb, 
                        contentDescription = null, 
                        tint = Color.White, 
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Consejo del día",
                    fontWeight = FontWeight.Black, 
                    color = Color.White,
                    fontSize = 17.sp
                )
                Text(
                    text = "Activa el volumen máximo para asegurar que tu alarma te despierte.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
