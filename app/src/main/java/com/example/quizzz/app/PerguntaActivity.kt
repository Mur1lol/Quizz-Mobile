package com.example.quizzz.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.quizzz.R
import com.example.quizzz.entidades.LoginResponse
import com.example.quizzz.servicos.LoginService
import com.example.quizzz.servicos.PontuacaoService
import com.example.quizzz.ui.CategoriaListListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pergunta.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerguntaActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: PontuacaoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergunta)

        //https://tads2019-todo-list.herokuapp.com/usuario/pontuacao

        var prefsUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        var email = prefsUsuario.getString("email", null)
        var senha = prefsUsuario.getString("senha", null)
        val pontos = intent.extras.getInt("ponto")

        configuraRetrofit()
        carregaDados(email, senha, pontos)

        btNovaPergunta.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }

        btRanking.setOnClickListener {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }

        btConfig.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }

        btDeslogar.setOnClickListener {
            var prefsUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE)
            var prefsDificuldade = getSharedPreferences("dificuldade", Context.MODE_PRIVATE)
            var prefsCategoria = getSharedPreferences("categoria", Context.MODE_PRIVATE)

            var edUser = prefsUsuario.edit()
            var edDif = prefsDificuldade.edit()
            var edCat = prefsCategoria.edit()

            edUser.putString("email", null)
            edUser.putString("senha", null)
            edUser.apply()

            edDif.putString("dificuldade", null)
            edDif.apply()

            edCat.putInt("categoria", 0)
            edCat.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun configuraRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/usuario/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PontuacaoService::class.java)
    }

    fun carregaDados(email: String, senha: String, pontos: Int) {
        service.putPontos(email, senha, pontos).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                var ponto: LoginResponse = response.body()!!
                if(response.body()!=null){
                    Toast.makeText(this@PerguntaActivity, getString(R.string.registrada), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}