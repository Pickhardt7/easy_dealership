package com.example.easy_dealership.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easy_dealership.components.BotaoPrincipal
import com.example.easy_dealership.components.Cabecalho
import com.example.easy_dealership.components.CinzaTexto
import com.example.easy_dealership.components.FundoCard
import com.example.easy_dealership.components.corDoStatus
import com.example.easy_dealership.components.fundoDoStatus
import com.example.easy_dealership.model.listaVagas
import com.example.easy_dealership.model.statusPorEtapa
import com.example.easy_dealership.model.veiculoNaVaga

@Composable
fun PatioScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Cabecalho("Localização no pátio")
        Text(
            "Toque em \"Ver\" para abrir o veículo da vaga.",
            fontSize = 13.sp,
            color = CinzaTexto,
            modifier = Modifier.padding(16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaVagas) { vaga ->
                VagaItem(vaga = vaga, navController = navController)
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            BotaoPrincipal(texto = "Voltar", onClick = { navController.popBackStack() })
        }
    }
}

@Composable
private fun VagaItem(vaga: String, navController: NavController) {
    val veiculo = veiculoNaVaga(vaga)
    if (veiculo == null) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = FundoCard)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(vaga, fontWeight = FontWeight.Bold)
                Text("Livre", fontSize = 12.sp, color = CinzaTexto)
            }
        }
    } else {
        val status = statusPorEtapa(veiculo.etapa)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = fundoDoStatus(status))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(vaga, fontWeight = FontWeight.Bold, color = corDoStatus(status))
                Text(status, fontSize = 11.sp, color = corDoStatus(status))
                Button(onClick = { navController.navigate("detalhes/${veiculo.id}") }) {
                    Text("Ver")
                }
            }
        }
    }
}
