package com.example.easy_dealership.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easy_dealership.components.BotaoPrincipal
import com.example.easy_dealership.components.Cabecalho
import com.example.easy_dealership.components.CinzaTexto

@Composable
fun CadastroScreen(navController: NavController) {
    var placa by rememberSaveable { mutableStateOf("") }
    var modelo by rememberSaveable { mutableStateOf("") }
    var vaga by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Cabecalho("Registrar chegada")
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = placa,
                onValueChange = { placa = it },
                label = { Text("Placa") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = modelo,
                onValueChange = { modelo = it },
                label = { Text("Modelo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = vaga,
                onValueChange = { vaga = it },
                label = { Text("Vaga") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Nesta versão os dados ainda não são gravados (banco de dados em uma próxima etapa).",
                fontSize = 12.sp,
                color = CinzaTexto
            )
            BotaoPrincipal(texto = "Salvar", onClick = { navController.popBackStack() })
            BotaoPrincipal(texto = "Voltar", onClick = { navController.popBackStack() })
        }
    }
}
