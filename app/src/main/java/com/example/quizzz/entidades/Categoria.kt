package com.example.quizzz.entidades

import androidx.room.PrimaryKey

class Categoria(

    var nome: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


