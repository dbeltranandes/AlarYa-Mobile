package com.example.alaryav10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alaryav10.ui.components.Boton_Volver_Atras
import com.example.alaryav10.ui.theme.OrangeAccent

@Composable
fun PantallaContextoWeb(nav_controller: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OrangeAccent)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Cabecera_Contexto_Web { nav_controller.popBackStack() }
        
        Spacer(Modifier.height(48.dp))
        
        Dibujo_Mundo_Ilustrativo()
        
        Spacer(Modifier.height(24.dp))
        
        Badge_Funcion_Smart()
        
        Spacer(Modifier.height(32.dp))
        
        Bloque_Explicativo_Feature()
        
        Spacer(Modifier.weight(1f))
        
        Boton_Para_Cerrar_Contexto { nav_controller.popBackStack() }
    }
}

@Composable
private fun Cabecera_Contexto_Web(accion_atras: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Boton_Volver_Atras(accion_atras)
        Spacer(Modifier.width(16.dp))
        Text(
            text = "Contexto Web",
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontSize = 22.sp
        )
    }
}

@Composable
private fun Dibujo_Mundo_Ilustrativo() {
    Surface(
        color = Color.White,
        shape = CircleShape,
        modifier = Modifier.size(140.dp),
        shadowElevation = 6.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Public, 
                contentDescription = null, 
                tint = OrangeAccent, 
                modifier = Modifier.size(72.dp)
            )
        }
    }
}


//revisar que este si esté colocandose bien
@Composable
private fun Badge_Funcion_Smart() {
    Surface(
        color = Color.White.copy(alpha = 0.9f), 
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome, 
                contentDescription = null, 
                tint = OrangeAccent, 
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "FUNCIÓN INTELIGENTE",
                color = OrangeAccent, 
                fontWeight = FontWeight.Black, 
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
private fun Bloque_Explicativo_Feature() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "¿Cómo funciona?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = Color.White
        )
        
        Spacer(Modifier.height(16.dp))
        
        Text(
            text = "Sincroniza con el modulo web. La alarma no sonará en festivos. \n - Detecta Festivos automáticamente" +
                    " \n - Sincroniza en tiempo real" +
                    " \n - Configuración desde la web",
            textAlign = TextAlign.Center,
            color = Color.White.copy(alpha = 0.95f),
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
private fun Boton_Para_Cerrar_Contexto(al_darle_clic: () -> Unit) {
    Button(
        onClick = al_darle_clic,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = OrangeAccent
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "Entendido",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp
        )
    }
}