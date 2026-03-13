package com.example.alaryav10.ui.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.Screen
import com.example.alaryav10.ui.theme.BluePrimary
import com.example.alaryav10.ui.theme.BlueSecondary
import com.example.alaryav10.ui.theme.GrayText


// como se decidio dividir las pantallas entonces utilizar estos componentes que son los que estan
//funcionando ya para las primeras pantallas
@Composable
fun MenuPrincipal_AlarYa(nav_controller: NavHostController, ruta_donde_estoy: String) {
    // la barra de abajo para saltar entre alarmas y ajustes.
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 10.dp 
    ) {
        val botones_nav = listOf(
            Triple("ALARMAS", Icons.Default.Alarm, Screen.AlarmList.route),
            Triple("AJUSTES", Icons.Default.Settings, Screen.Settings.route)
        )

        botones_nav.forEach { (nombre_boton, icono_dibujo, destino_ruta) ->
            NavigationBarItem(
                selected = ruta_donde_estoy == destino_ruta,
                onClick = { 
                    // solo navegamos si no estamos ya en esa pantalla para no recargar
                    if (ruta_donde_estoy != destino_ruta) {
                        nav_controller.navigate(destino_ruta) {
                            popUpTo(Screen.AlarmList.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(icono_dibujo, contentDescription = nombre_boton) },
                label = { Text(nombre_boton, fontWeight = FontWeight.ExtraBold, fontSize = 11.sp) }
            )
        }
    }
}

@Composable
fun Fila_AjusteManual(
    icono_ajuste: ImageVector, 
    texto_mostrar: String, 
    con_flechita: Boolean = false,
    accion_click: (() -> Unit)? = null
) {
    // fila para las opciones
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .then(if (accion_click != null) Modifier.clickable { accion_click() } else Modifier),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Marco_Para_Icono(icono_ajuste)
            Spacer(Modifier.width(16.dp))
            Text(
                text = texto_mostrar, 
                modifier = Modifier.weight(1f), 
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.8f)
            )
            if (con_flechita) {
                Icon(
                    imageVector = Icons.Default.ChevronRight, 
                    contentDescription = null, 
                    tint = Color.Gray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun Opcion_Con_Switch_Manual(
    icono_opc: ImageVector, 
    texto_etiqueta: String, 
    valor_arranque: Boolean,
    al_cambiar: (Boolean) -> Unit = {}
) {
   // la fila que tiene switch
    var estado_actual by remember { mutableStateOf(valor_arranque) }
    
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Marco_Para_Icono(icono_opc)
            Spacer(Modifier.width(16.dp))
            Text(
                text = texto_etiqueta, 
                modifier = Modifier.weight(1f), 
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Switch(
                checked = estado_actual, 
                onCheckedChange = { 
                    estado_actual = it 
                    al_cambiar(it)
                },
                colors = SwitchDefaults.colors(checkedTrackColor = BluePrimary)
            )
        }
    }
}

@Composable
private fun Marco_Para_Icono(vector_icon: ImageVector) {
    Surface(
        modifier = Modifier.size(44.dp),
        color = BluePrimary.copy(alpha = 0.12f),
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = vector_icon, 
                contentDescription = null, 
                tint = BluePrimary, 
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun Boton_Volver_Atras(al_regresar: () -> Unit) {
    // boton redondo para devolverse
    IconButton(
        onClick = al_regresar,
        modifier = Modifier
            .size(48.dp)
            .background(BlueSecondary.copy(alpha = 0.85f), CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack, 
            contentDescription = "Ir atrás", 
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun Nav_Inferior_Bienvenida(control_nav: NavHostController, pantalla_donde_estoy: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), 
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val rutas_aux = listOf(
            "INICIO" to Icons.Default.Home,
            "AYUDA" to Icons.Default.HelpOutline
        )
        
        rutas_aux.forEach { (nombre_txt, icono_v) ->
            val esta_marcado = (nombre_txt == "INICIO" && pantalla_donde_estoy == Screen.Welcome.route) || 
                               (nombre_txt == "AYUDA" && pantalla_donde_estoy == Screen.HowItWorks.route)
            
            Item_Nav_Mini(nombre_txt, icono_v, esta_marcado) {
                if (nombre_txt == "INICIO") control_nav.navigate(Screen.Welcome.route)
                else control_nav.navigate(Screen.HowItWorks.route)
            }
        }
    }
}

@Composable
private fun Item_Nav_Mini(
    etiqueta_nav: String, 
    img_nav: ImageVector, 
    status_activo: Boolean, 
    on_click: () -> Unit
) {
    val color_pintar = if (status_activo) BluePrimary else GrayText.copy(alpha = 0.6f)
    
    Column(
        modifier = Modifier
            .clickable(onClick = on_click)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(img_nav, null, tint = color_pintar, modifier = Modifier.size(26.dp))
        Text(
            text = etiqueta_nav, 
            fontSize = 12.sp, 
            fontWeight = FontWeight.Black, 
            color = color_pintar,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun Fila_Para_Elegir_Cuenta(
    nombre_u: String, 
    email_u: String, 
    letra_p: String?, 
    al_clic: () -> Unit
) {
    // este es para la parte de google,  solo el diseño
    // revisar con carlos cuando termine la pantalla
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = al_clic)
            .padding(vertical = 14.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = Color(0xFFF1F3F4),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f))
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (letra_p != null) {
                    Text(
                        text = letra_p, 
                        fontWeight = FontWeight.Black, 
                        color = Color.DarkGray
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person, 
                        contentDescription = null, 
                        modifier = Modifier.size(22.dp), 
                        tint = Color.Gray
                    )
                }
            }
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = nombre_u, 
                fontWeight = FontWeight.ExtraBold, 
                fontSize = 16.sp,
                color = Color.Black
            )
            if (email_u.isNotEmpty()) {
                Text(
                    text = email_u, 
                    fontSize = 13.sp, 
                    color = GrayText
                )
            }
        }
    }
}
