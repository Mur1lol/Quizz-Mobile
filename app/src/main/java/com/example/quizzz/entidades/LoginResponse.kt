package com.example.quizzz.entidades

data class LoginResponse(
    var sucesso: Boolean,
    var mensagem: String,
    var pontuacao: Int
)