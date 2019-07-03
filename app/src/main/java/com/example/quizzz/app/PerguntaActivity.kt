package com.example.quizzz.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizzz.R
import com.example.quizzz.ui.CategoriaListListener

class PerguntaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergunta)

        Log.e("PONTOSSS",intent.extras.getString("ponto"))
    }
}
