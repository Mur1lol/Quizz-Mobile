package com.example.quizzz.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import com.example.quizzz.entidades.Cadastro
import com.example.quizzz.servicos.CadastroService
import kotlinx.android.synthetic.main.activity_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CadastroActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: CadastroService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //https://tads2019-todo-list.herokuapp.com/usuario/registrar

        configuraRetrofit()

        btCadastro.setOnClickListener {
            if(txtSenha.text.toString() == txtConfirma.text.toString()){
                cadastraUsuario()
            }
        }
    }

    fun configuraRetrofit() {
        retrofit = Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/usuario/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(CadastroService::class.java)
    }

    private fun cadastraUsuario(){
        val nome = txtNome.text.toString()
        val email = txtEmail.text.toString()
        val senha = txtSenha.text.toString()

        service.getCadastro(nome, email, senha).enqueue(object : Callback<Cadastro> {
            override fun onFailure(call: Call<Cadastro>, t: Throwable) {
                Toast.makeText(this@CadastroActivity, "Mensagem de erro!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Cadastro>, response: Response<Cadastro>) {
                Toast.makeText(this@CadastroActivity, "Mensagem de sucesso!", Toast.LENGTH_SHORT).show()
                //Condição pra ver se o email não é repetido
            }

        })
    }

}
