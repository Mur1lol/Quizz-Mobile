package com.example.quizzz.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trivia_categories")
data class Categoria(
    @SerializedName("name")
    var nome: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


