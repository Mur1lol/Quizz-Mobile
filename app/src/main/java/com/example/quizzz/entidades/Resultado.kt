package com.example.quizzz.entidades

data class Resultado (
    var response_code: String,
    var results: List<Pergunta>
)