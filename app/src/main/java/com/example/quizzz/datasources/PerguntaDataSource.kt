package com.example.quizzz.datasources

interface PerguntaDataSource {
    fun getPerguntas (
        qtde: Integer,
        categoria: Integer,
        dificuldade: String
    )
}