package com.flaviaprouvot.livrosfirebase.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    val nome = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val mensagem = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF57C00) // Laranja escuro
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = nome.value,
            onValueChange = {
                nome.value = it.trim()
                mensagem.value = ""
            },
            label = { Text("Nome", color = Color(0xFFF57C00)) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = senha.value,
            onValueChange = {
                senha.value = it.trim()
                mensagem.value = ""
            },
            label = { Text("Senha", color = Color(0xFFF57C00)) }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    if (nome.value.isNotEmpty() && senha.value.isNotEmpty()) {
                        mensagem.value = "✔ Login bem-sucedido!"
                        navController.navigate("ListaLivros")
                    } else {
                        mensagem.value = "❌ Preencha todos os campos."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Entrar", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { navController.navigate("CadastroLivros") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Cadastro", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (mensagem.value.isNotEmpty()) {
            Text(
                text = mensagem.value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (mensagem.value.contains("sucesso")) Color(0xFF4CAF50) else Color.Red
            )
        }
    }
}

