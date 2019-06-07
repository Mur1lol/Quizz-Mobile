package com.example.quizzz.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "perguntas")
data class Pergunta (
     @SerializedName("category")
     var categoria: String,
     @SerializedName("type")
     var tipo: String,
     @SerializedName("difficulty")
     var dificuldade: String,
     @SerializedName("question")
     var questao: String,
     @SerializedName("correct_answer")
     var resposta_correta: String,
     @SerializedName("incorrect_answers")
     var respostas_incorretas: List<String>
) {
     @PrimaryKey(autoGenerate = true)
     var id: Long = 0
}

