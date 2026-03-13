package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alaryav10.ui.components.MenuPrincipal_AlarYa
import com.example.alaryav10.ui.theme.*

// modelo básico para las alarmas.
data class Alarma_Item(
    val id: Int,
    val hora: String,
    val reto: String,
    val dias: String,
    val activa: Boolean
)
private val radio_borde_card = 22.dp

@Composable
fun PantallaListaAlarmas(nav_controller: NavHostController) {
    // alarmas de prueba
    val lista_de_alarmas = remember {
        mutableStateListOf(
            Alarma_Item(1, "07:00 AM", "DESAFÍO MATEMÁTICO", "Lun - Vie", true),
            Alarma_Item(2, "08:30 AM", "PASOS (20)", "Sáb, Dom", true),
        )
    }

    val pincel_fondo = Brush.verticalGradient(listOf(BluePrimary, BlueSecondary))

    Scaffold(
        bottomBar = { MenuPrincipal_AlarYa(nav_controller, Screen.AlarmList.route) }
    ) { rellenos_scaffold ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(pincel_fondo)
                .padding(rellenos_scaffold)
                .padding(horizontal = 22.dp)
        ) {
            Cabecera_De_La_Lista(nav_controller)

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(lista_de_alarmas, key = { it.id }) { item_alarma ->
                    Fila_Alarma_Individual(
                        datos = item_alarma,
                        al_clic_switch = { valor_nuevo ->
                            val pos = lista_de_alarmas.indexOfFirst { it.id == item_alarma.id }
                            if (pos != -1) {
                                lista_de_alarmas[pos] = item_alarma.copy(activa = valor_nuevo)
                            }
                        }
                    )
                }

                item {
                    // Botón para agregar al final de la lista
                    Boton_Para_Crear_Nueva { nav_controller.navigate(Screen.CreateAlarm.route) }
                }
            }
        }
    }
}

@Composable
private fun Cabecera_De_La_Lista(nav: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Mis Alarmas",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
        IconButton(
            onClick = { nav.navigate(Screen.HowItWorks.route) }
        ) {
            Icon(
                imageVector = Icons.Default.Info, 
                contentDescription = "Ayuda",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun Fila_Alarma_Individual(
    datos: Alarma_Item,
    al_clic_switch: (Boolean) -> Unit
) {
    val opacidad = if (datos.activa) 1f else 0.55f
    val fondo_card = if (datos.activa) Color.White else Color.White.copy(0.85f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {},
        shape = RoundedCornerShape(radio_borde_card),
        colors = CardDefaults.cardColors(containerColor = fondo_card),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = datos.hora,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black.copy(alpha = opacidad)
                )
                
                // Un pequeño tag para el reto
                Surface(
                    color = if (datos.activa) BlueSecondary else Color.LightGray,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    Text(
                        text = datos.reto,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                    )
                }
                
                Text(
                    text = datos.dias,
                    fontSize = 13.sp,
                    color = GrayText.copy(alpha = opacidad)
                )
            }

            Switch(
                checked = datos.activa,
                onCheckedChange = al_clic_switch,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = BluePrimary,
                    uncheckedTrackColor = Color.Gray.copy(0.3f)
                )
            )
        }
    }
}

@Composable
private fun Boton_Para_Crear_Nueva(on_click_crear: () -> Unit) {
    Button(
        onClick = on_click_crear,
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp),
        colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {
        Icon(Icons.Default.Add, contentDescription = null)
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Añadir alarma",
            fontWeight = FontWeight.Black,
            fontSize = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_Alarmas_Human() {
    AlarYaV10Theme {
        PantallaListaAlarmas(nav_controller = rememberNavController())
    }
}
