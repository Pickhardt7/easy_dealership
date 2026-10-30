package com.example.easy_dealership.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easy_dealership.components.Azul
import com.example.easy_dealership.components.BotaoPrincipal
import com.example.easy_dealership.components.Cabecalho
import com.example.easy_dealership.components.FundoCard
import com.example.easy_dealership.components.Marinho
import com.example.easy_dealership.components.VeiculoCard
import com.example.easy_dealership.model.*

@Composable
fun VeiculosScreen(navController: NavController) {
    var busca by rememberSaveable { mutableStateOf("") }
    var filtro by rememberSaveable { mutableStateOf("Todos") }
    val opcoes = listOf("Todos", STATUS_PENDENTE, STATUS_ANDAMENTO, STATUS_PRONTO)
    val lista = filtrarVeiculos(busca, filtro)

    Column(modifier = Modifier.fillMaxSize()) {
        Cabecalho("Veículos")
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = busca,
                onValueChange = { busca = it },
                label = { Text("Buscar (modelo ou placa)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(opcoes) { opcao ->
                    val selecionado = opcao == filtro
                    Button(
                        onClick = { filtro = opcao },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selecionado) Azul else FundoCard,
                            contentColor = if (selecionado) Color.White else Marinho
                        )
                    ) {
                        Text(opcao)
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(lista) { v ->
                VeiculoCard(veiculo = v, onVerDetalhes = { navController.navigate("detalhes/${v.id}") })
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            BotaoPrincipal(texto = "Voltar", onClick = { navController.popBackStack() })
        }
    }
}
