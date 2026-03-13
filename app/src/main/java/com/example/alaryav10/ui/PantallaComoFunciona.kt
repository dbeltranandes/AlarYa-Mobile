package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.Boton_Volver_Atras
import com.example.alaryav10.ui.theme.*


@Composable
fun PantallaComoFunciona(nav_controller: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BluePrimary)
            .padding(24.dp)
    ) {
        Encabezado_Pasos_Guia { nav_controller.popBackStack() }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Lista_De_Pasos_Visual(nav_controller)
    }
}

@Composable
private fun Encabezado_Pasos_Guia(al_atras: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Boton_Volver_Atras(al_atras)
        Spacer(Modifier.width(16.dp))
        Text(
            text = "Cómo funciona",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
private fun Lista_De_Pasos_Visual(nav: NavHostController) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Tarjeta_Destacada_Info(
                t_titulo = "Desafía tu mente",
                t_sub = "Alarma inteligente",
                t_desc = "AlarYa no es una alarma común. El sonido no se detendrá hasta que completes la tarea que elegiste.",
                img_ico = Icons.Default.Psychology
            )
        }
        
        item { Renglon_Paso_Tutorial("1", Icons.Default.Settings, "Configura tu alarma", "Elige la hora y tu tipo de misión.") }
        item { Renglon_Paso_Tutorial("2", Icons.Default.MusicNote, "La alarma suena", "El sonido es continuo y molesto.") }
        item { Renglon_Paso_Tutorial("3", Icons.Default.TaskAlt, "Completa el desafío", "Resuelve tareas para detener el ruido.") }
        
        item {
            Banner_Logro_Mision()
            Spacer(Modifier.height(16.dp))
            Boton_Ok_Entendido { nav.popBackStack() }
        }
    }
}

@Composable
private fun Tarjeta_Destacada_Info(t_titulo: String, t_sub: String, t_desc: String, img_ico: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = OrangeAccent
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(img_ico, null, tint = Color.White, modifier = Modifier.size(28.dp))
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = t_titulo, fontWeight = FontWeight.Black, fontSize = 20.sp)
                    Text(text = t_sub, color = BlueSecondary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = t_desc, 
                color = GrayText, 
                lineHeight = 22.sp,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
private fun Renglon_Paso_Tutorial(num: String, icono_v: ImageVector, t_paso: String, d_paso: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Surface(
                    modifier = Modifier.size(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = BlueSecondary
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(icono_v, null, tint = Color.White, modifier = Modifier.size(24.dp))
                    }
                }
                // el numerito del paso en la esquina
                Surface(
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 6.dp, y = (-6).dp),
                    shape = CircleShape,
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(num, fontSize = 11.sp, fontWeight = FontWeight.Black)
                    }
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = t_paso, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Text(text = d_paso, fontSize = 14.sp, color = GrayText, lineHeight = 18.sp)
            }
        }
    }
}

@Composable
private fun Banner_Logro_Mision() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = GreenAccent)
    ) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.EmojiEvents, null, tint = Color.White, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(12.dp))
            Text("¡Misión Cumplida!", color = Color.White, fontWeight = FontWeight.Black, fontSize = 22.sp)
            Text(
                text = "Gana puntos y desbloquea logros.",
                color = Color.White.copy(alpha = 0.9f), 
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun Boton_Ok_Entendido(accion_ok: () -> Unit) {
    Button(
        onClick = accion_ok,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePrimary),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text("Entendido", fontWeight = FontWeight.Black, fontSize = 18.sp)
    }
}
