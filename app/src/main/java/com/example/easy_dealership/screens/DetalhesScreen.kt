package com.example.easy_dealership.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.easy_dealership.components.StatusTag
import com.example.easy_dealership.model.ETAPAS
import com.example.easy_dealership.model.buscarVeiculo
import com.example.easy_dealership.model.statusPorEtapa

@Composable
fun DetalhesScreen(navController: NavController, id: String?) {
    // A rota carrega só o id; os dados completos são buscados aqui
    val veiculo = buscarVeiculo(id?.toIntOrNull() ?: -1)
    var etapa by remember { mutableStateOf(veiculo?.etapa ?: 0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Cabecalho("Detalhe do veículo")
        if (veiculo == null) {
            Text("Veículo não encontrado.", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = FundoCard)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(veiculo.modelo, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                StatusTag(statusPorEtapa(etapa))
                            }
                            Text(
                                "Placa ${veiculo.placa} · Vaga ${veiculo.vaga} · Chegada ${veiculo.dataChegada}",
                                fontSize = 13.sp,
                                color = CinzaTexto
                            )
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = FundoCard)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Histórico do workflow", fontWeight = FontWeight.Bold)
                            for (i in ETAPAS.indices) {
                                val situacao = if (i < etapa) "Concluída"
                                else if (i == etapa) "Etapa atual"
                                else "Pendente"
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Checkbox(checked = i < etapa, onCheckedChange = null)
                                    Column {
                                        Text(
                                            ETAPAS[i],
                                            fontWeight = if (i == etapa) FontWeight.Bold else FontWeight.Normal
                                        )
                                        Text(situacao, fontSize = 12.sp, color = CinzaTexto)
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    BotaoPrincipal(
                        texto = "Atualizar etapa",
                        onClick = { if (etapa < 4) etapa = etapa + 1 }
                    )
                }
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            BotaoPrincipal(texto = "Voltar", onClick = { navController.popBackStack() })
        }
    }
}
