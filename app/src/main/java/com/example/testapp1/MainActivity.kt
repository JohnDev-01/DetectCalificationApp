package com.example.testapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp1.ui.theme.Testapp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Testapp1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    var gradeInput by remember { mutableStateOf("") }
    var gradeResult by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado con nombre, matrícula y foto
        HeaderSection()

        Spacer(modifier = Modifier.height(20.dp))

        // Campo de entrada para calificación
        Text(
            text = "Ingrese una calificación (0-100):",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        BasicTextField(
            value = gradeInput,
            onValueChange = { gradeInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(50.dp)
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                ) {
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para convertir la calificación
        Button(onClick = {
            gradeResult = convertGradeToLetter(gradeInput)
        }) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Resultado en letra
        Text(
            text = "Resultado: $gradeResult",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto personal (reemplaza con tu foto en res/drawable)
        Image(
            painter = painterResource(id = R.drawable.yo), // Asegúrate de agregar la foto a drawable.
            contentDescription = "Mi Foto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )

        // Nombre y matrícula
        Text(text = "Nombre: John Doe", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Matrícula: 20250001", fontSize = 16.sp)
    }
}

fun convertGradeToLetter(grade: String): String {
    return try {
        val numGrade = grade.toInt()
        when {
            numGrade >= 90 -> "A"
            numGrade >= 80 -> "B"
            numGrade >= 70 -> "C"
            else -> "F"
        }
    } catch (e: NumberFormatException) {
        "Calificación inválida"
    }
}
