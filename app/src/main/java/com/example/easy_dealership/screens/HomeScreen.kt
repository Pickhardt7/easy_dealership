package com.example.easy_dealership.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easy_dealership.components.BotaoPrincipal
import com.example.easy_dealership.components.Cabecalho
import com.example.easy_dealership.components.CardContador
import com.example.easy_dealership.components.CinzaTexto
import com.example.easy_dealership.components.FundoCard
import com.example.easy_dealership.components.VeiculoCard
import com.example.easy_dealership.model.*

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Cabecalho("Painel do pátio")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = FundoCard)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("Veículos no pátio", fontSize = 12.sp, color = CinzaTexto)
                        Text("${listaVeiculos.size}", fontSize = 34.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CardContador(STATUS_PENDENTE, contarPorStatus(STATUS_PENDENTE), Modifier.weight(1f))
                    CardContador(STATUS_ANDAMENTO, contarPorStatus(STATUS_ANDAMENTO), Modifier.weight(1f))
                    CardContador(STATUS_PRONTO, contarPorStatus(STATUS_PRONTO), Modifier.weight(1f))
                }
            }
            item {
                BotaoPrincipal(texto = "Ver todos os veículos", onClick = { navController.navigate("veiculos") })
            }
            item {
                BotaoPrincipal(texto = "Mapa do pátio", onClick = { navController.navigate("patio") })
            }
            item {
                BotaoPrincipal(texto = "Registrar chegada", onClick = { navController.navigate("cadastro") })
            }
            item {
                Text("Chegadas de hoje", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            items(veiculosChegadosHoje()) { v ->
                VeiculoCard(veiculo = v, onVerDetalhes = { navController.navigate("detalhes/${v.id}") })
            }
            item {
                Text("Parados há mais de $DIAS_ALERTA dias", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            items(veiculosParados()) { v ->
                VeiculoCard(veiculo = v, onVerDetalhes = { navController.navigate("detalhes/${v.id}") })
            }
        }
    }
}
