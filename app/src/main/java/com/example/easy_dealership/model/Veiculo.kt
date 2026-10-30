package com.example.easy_dealership.model

data class Veiculo(
    val id: Int,
    val placa: String,
    val modelo: String,
    val vaga: String,
    val dataChegada: String,
    val diasNoPatio: Int,
    val etapa: Int // posição na lista ETAPAS
)

const val STATUS_PENDENTE = "Pendente de vistoria"
const val STATUS_ANDAMENTO = "Em andamento"
const val STATUS_PRONTO = "Pronto para venda"
const val DIAS_ALERTA = 5

val ETAPAS = listOf(
    "Chegada ao pátio",
    "Vistoria",
    "Preparação",
    "Pronto para venda",
    "Entregue ao cliente"
)

fun statusPorEtapa(etapa: Int): String {
    if (etapa <= 1) return STATUS_PENDENTE
    if (etapa == 2) return STATUS_ANDAMENTO
    if (etapa == 3) return STATUS_PRONTO
    return "Entregue"
}
