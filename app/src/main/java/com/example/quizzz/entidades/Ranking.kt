package com.example.quizzz.entidades

import androidx.room.PrimaryKey

data class Ranking (
    var nome: String,
    var pontuacao: Int,
    var partidasJogadas: Int,
    var ultimaPartida: java.util.Date
)