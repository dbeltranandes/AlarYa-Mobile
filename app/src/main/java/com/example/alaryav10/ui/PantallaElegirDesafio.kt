package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.alaryav10.ui.components.Boton_Volver_Atras
import com.example.alaryav10.ui.theme.BluePrimary
import com.example.alaryav10.ui.theme.BlueSecondary
import com.example.alaryav10.ui.theme.OrangeAccent

@Composable
fun PantallaElegirDesafio(nav_controller: NavHostController) {
    // logica simple para saber desafio seleccionado
    var reto_seleccionado by remember { mutableStateOf("Mates") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePrimary)
            .padding(24.dp)
    ) {
        Cabecera_Seleccion_Mision { nav_controller.popBackStack() }

        Spacer(Modifier.height(32.dp))

        Tarjeta_Recordatorio_Mision()

        Spacer(Modifier.height(32.dp))

        Grilla_De_Retos_Visual(
            id_seleccionado = reto_seleccionado,
            al_elegir = { reto_seleccionado = it }
        )

        Spacer(Modifier.weight(1f))

        Boton_Confirmar_Mision {
            nav_controller.navigate(Screen.CreateAudio.route) 
        }
    }
}

@Composable
private fun Cabecera_Seleccion_Mision(on_back: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Boton_Volver_Atras(on_back)
        Spacer(Modifier.width(16.dp))
        Text(
            text = "Elige tu desafío",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
private fun Tarjeta_Recordatorio_Mision() {
    // tarjeta contexto
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(14.dp),
                color = OrangeAccent.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Bolt, 
                        contentDescription = null, 
                        tint = OrangeAccent,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = "Mantente despierto",
                    fontWeight = FontWeight.Black, 
                    fontSize = 18.sp
                )
                Text(
                    text = "Selecciona una tarea obligatoria para desactivar la alarma.",
                    color = BlueSecondary, 
                    fontSize = 13.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun Grilla_De_Retos_Visual(id_seleccionado: String, al_elegir: (String) -> Unit) {
    // retos en filas de dos
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Item_De_Reto_Individual(
                label = "Matemáticas", 
                img_vector = Icons.Default.Calculate, 
                esta_activo = id_seleccionado == "Mates", 
                modifier = Modifier.weight(1f),
                on_click_item = { al_elegir("Mates") }
            )
            Item_De_Reto_Individual(
                label = "Escanear QR", 
                img_vector = Icons.Default.QrCodeScanner, 
                esta_activo = id_seleccionado == "QR", 
                modifier = Modifier.weight(1f),
                on_click_item = { al_elegir("QR") }
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Item_De_Reto_Individual(
                label = "Grabar Audio", 
                img_vector = Icons.Default.Mic, 
                esta_activo = id_seleccionado == "Audio", 
                modifier = Modifier.weight(1f),
                on_click_item = { al_elegir("Audio") }
            )
            Item_De_Reto_Individual(
                label = "Pasos", 
                img_vector = Icons.Default.DirectionsWalk, 
                esta_activo = id_seleccionado == "Pasos", 
                modifier = Modifier.weight(1f),
                on_click_item = { al_elegir("Pasos") }
            )
        }
    }
}

@Composable
private fun Item_De_Reto_Individual(
    label: String,
    img_vector: ImageVector,
    esta_activo: Boolean,
    modifier: Modifier,
    on_click_item: () -> Unit
) {

    val bg_color = if (esta_activo) BlueSecondary else Color.White.copy(alpha = 0.9f)
    val content_color = if (esta_activo) Color.White else BluePrimary

    Card(
        modifier = modifier
            .height(130.dp)
            .clickable(onClick = on_click_item),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = bg_color),
        elevation = CardDefaults.cardElevation(defaultElevation = if (esta_activo) 8.dp else 0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = img_vector,
                contentDescription = null,
                tint = content_color,
                modifier = Modifier.size(42.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = label,
                fontWeight = FontWeight.Black,
                color = if (esta_activo) Color.White else Color.Black,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun Boton_Confirmar_Mision(al_darle_click: () -> Unit) {
    Button(
        onClick = al_darle_click,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueSecondary,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Crear Alarma", 
            fontWeight = FontWeight.Black, 
            fontSize = 18.sp
        )
    }
}
