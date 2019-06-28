package com.example.quizzz.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import com.example.quizzz.entidades.Cadastro
import com.example.quizzz.entidades.CadastroResponse
import com.example.quizzz.entidades.LoginResponse
import com.example.quizzz.servicos.CadastroService
import com.example.quizzz.servicos.LoginService
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

        service.getCadastro(nome, email, senha).enqueue(object : Callback<CadastroResponse> {
            override fun onFailure(call: Call<CadastroResponse>, t: Throwable) {
                Toast.makeText(this@CadastroActivity, "Mensagem de erro!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<CadastroResponse>, response: Response<CadastroResponse>) {
                if(response.body()!=null && senha.length>=6 ) {
                    var cadastro: CadastroResponse = response.body()!!

                    if (cadastro.sucesso) {
                        Toast.makeText(this@CadastroActivity, cadastro.mensagem, Toast.LENGTH_SHORT).show()
                        abrirConfigActivity()
                    }
                }
                else {
                        Toast.makeText(this@CadastroActivity, "erro ao cadastrar", Toast.LENGTH_SHORT).show()
                    }

            }

        })
    }

    private fun abrirConfigActivity() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }

}
