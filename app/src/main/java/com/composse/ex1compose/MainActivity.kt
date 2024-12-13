package com.composse.ex1compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composse.ex1compose.ui.theme.Ex1ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex1ComposeTheme {
                PreviewTexto()
            }
        }
    }
}
@Composable
fun TextoCambiante(name: String){
    val backgroundColor = remember { mutableStateOf(Color.White) }
    val nombre = remember { mutableStateOf(name) }
    val nombresLista = remember { SnapshotStateList<String>() }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = backgroundColor.value),
        contentAlignment = Alignment.Center

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Hello $name",
                color = Color.Red,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            val buttonText = remember { mutableStateOf("Change Name") }

            Button(onClick = { buttonText.value = if(buttonText.value == "Change Name") "Presiona Aquí" else "Change Name"
            }) {
                Text(
                    text = buttonText.value,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                backgroundColor.value = if (backgroundColor.value == Color.White) Color.Gray else Color.White
            }) {
                Text(
                    text = "Cambiar fondo",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nombre.value,
                onValueChange = { nombre.value = it },
                label = { Text("Introducir Nombre") },
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (nombre.value.isNotBlank()) {
                    nombresLista.add(nombre.value)
                }
            }) {
                Text(
                    text = "Añadir Nombre",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lista de Nombres",
                fontSize = 20.sp,
                color = Color.Black
            )
            nombresLista.forEach {
                Text(
                    text = it,
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}
@Preview
@Composable
fun PreviewTexto() {
    TextoCambiante("Yoel")
}