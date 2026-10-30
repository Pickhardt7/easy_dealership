package com.example.easy_dealership.model

// Dados simulados (Checkpoint 1: ainda sem banco de dados)
val listaVeiculos = listOf(
    Veiculo(1, "ABC1D23", "Chevrolet Onix 1.0", "A3", "29/10", 0, 1),
    Veiculo(2, "EFG4H56", "Hyundai HB20 1.6", "B1", "29/10", 0, 1),
    Veiculo(3, "IJK7L89", "VW Gol 1.0", "C2", "23/10", 6, 2),
    Veiculo(4, "MNO1P23", "Fiat Argo 1.3", "B4", "26/10", 3, 3),
    Veiculo(5, "QRS4T56", "Toyota Corolla 2.0", "A1", "28/10", 1, 2),
    Veiculo(6, "UVW7X89", "Honda Civic 2.0", "B2", "20/10", 9, 3),
    Veiculo(7, "YZA2B34", "Renault Kwid 1.0", "B3", "25/10", 4, 3),
    Veiculo(8, "CDE5F67", "Jeep Renegade 1.8", "C3", "22/10", 7, 2)
)

val listaVagas = listOf(
    "A1", "A2", "A3", "A4",
    "B1", "B2", "B3", "B4",
    "C1", "C2", "C3", "C4"
)

fun buscarVeiculo(id: Int): Veiculo? {
    for (v in listaVeiculos) {
        if (v.id == id) return v
    }
    return null
}

fun veiculoNaVaga(vaga: String): Veiculo? {
    for (v in listaVeiculos) {
        if (v.vaga == vaga) return v
    }
    return null
}

fun contarPorStatus(status: String): Int {
    var total = 0
    for (v in listaVeiculos) {
        if (statusPorEtapa(v.etapa) == status) total = total + 1
    }
    return total
}

fun veiculosChegadosHoje(): List<Veiculo> {
    val resultado = mutableListOf<Veiculo>()
    for (v in listaVeiculos) {
        if (v.diasNoPatio == 0) resultado.add(v)
    }
    return resultado
}

fun veiculosParados(): List<Veiculo> {
    val resultado = mutableListOf<Veiculo>()
    for (v in listaVeiculos) {
        if (v.diasNoPatio > DIAS_ALERTA) resultado.add(v)
    }
    return resultado
}

// filtro = "Todos" ou um dos status
fun filtrarVeiculos(busca: String, filtro: String): List<Veiculo> {
    val resultado = mutableListOf<Veiculo>()
    for (v in listaVeiculos) {
        val combinaBusca = v.modelo.contains(busca, ignoreCase = true) ||
            v.placa.contains(busca, ignoreCase = true)
        val combinaStatus = filtro == "Todos" || statusPorEtapa(v.etapa) == filtro
        if (combinaBusca && combinaStatus) resultado.add(v)
    }
    return resultado
}
