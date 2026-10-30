package com.example.easy_dealership.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easy_dealership.model.STATUS_ANDAMENTO
import com.example.easy_dealership.model.STATUS_PENDENTE
import com.example.easy_dealership.model.Veiculo
import com.example.easy_dealership.model.statusPorEtapa

// Cores do app (Material Design: cores e temas)
val Azul = Color(0xFF174A9C)
val Marinho = Color(0xFF12263A)
val CinzaTexto = Color(0xFF4A5A6A)
val FundoCard = Color(0xFFEEF2F7)

fun corDoStatus(status: String): Color {
    if (status == STATUS_PENDENTE) return Color(0xFF8A4B08)
    if (status == STATUS_ANDAMENTO) return Color(0xFF174A9C)
    return Color(0xFF146B3E)
}

fun fundoDoStatus(status: String): Color {
    if (status == STATUS_PENDENTE) return Color(0xFFFDEBD3)
    if (status == STATUS_ANDAMENTO) return Color(0xFFDCE9FB)
    return Color(0xFFD7F0E1)
}

@Composable
fun BotaoPrincipal(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Azul)
    ) {
        Text(texto)
    }
}

@Composable
fun Cabecalho(titulo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Marinho)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Concessionária · Gestor", color = Color.White, fontSize = 13.sp)
            Text(titulo, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StatusTag(status: String) {
    Card(colors = CardDefaults.cardColors(containerColor = fundoDoStatus(status))) {
        Text(
            text = status,
            color = corDoStatus(status),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun CardContador(status: String, quantidade: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = fundoDoStatus(status))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("$quantidade", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = corDoStatus(status))
            Text(status, fontSize = 12.sp, color = corDoStatus(status))
        }
    }
}

@Composable
fun VeiculoCard(veiculo: Veiculo, onVerDetalhes: () -> Unit) {
    val status = statusPorEtapa(veiculo.etapa)
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
                Column(modifier = Modifier.weight(1f)) {
                    Text(veiculo.modelo, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(veiculo.placa, fontSize = 12.sp, color = CinzaTexto)
                }
                StatusTag(status)
            }
            Text(
                "${veiculo.diasNoPatio} dia(s) no pátio · Vaga ${veiculo.vaga}",
                fontSize = 12.sp,
                color = CinzaTexto
            )
            BotaoPrincipal(texto = "Ver detalhes", onClick = onVerDetalhes)
        }
    }
}
