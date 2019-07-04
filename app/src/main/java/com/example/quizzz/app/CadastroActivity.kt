package com.example.quizzz.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import com.example.quizzz.entidades.CadastroResponse
import com.example.quizzz.servicos.CadastroService
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*
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
                esconderBotao()
                cadastraUsuario(txtNome.text.toString(), txtEmail.text.toString(), txtSenha.text.toString())
            }
            else {
                Toast.makeText(this, getString(R.string.errada), Toast.LENGTH_SHORT).show()
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

    private fun cadastraUsuario(nome: String, email: String, senha: String){
        service.getCadastro(nome, email, senha).enqueue(object : Callback<CadastroResponse> {
            override fun onFailure(call: Call<CadastroResponse>, t: Throwable) {
                Toast.makeText(this@CadastroActivity, getString(R.string.internet), Toast.LENGTH_SHORT).show()
                mostraBotao()
            }

            override fun onResponse(call: Call<CadastroResponse>, response: Response<CadastroResponse>) {
                var cadastro: CadastroResponse = response.body()!!
                if(response.body()!=null && senha.length>=6 ) {

                    if (cadastro.sucesso) {
                        var prefsUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                        var edUser = prefsUsuario.edit()

                        edUser.putString("email", txtEmail.text.toString())
                        edUser.putString("senha", txtSenha.text.toString())
                        edUser.apply()

                        Toast.makeText(this@CadastroActivity, cadastro.mensagem, Toast.LENGTH_SHORT).show()
                        abrirConfigActivity()
                    }
                    else {
                        mostraBotao()
                        Toast.makeText(this@CadastroActivity, cadastro.mensagem, Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    mostraBotao()
                    Toast.makeText(this@CadastroActivity, cadastro.mensagem, Toast.LENGTH_SHORT).show()
                }

            }

        })
    }

    private fun abrirConfigActivity() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }

    fun esconderBotao() {
        btCadastro.visibility = View.INVISIBLE
        Toast.makeText(this, getString(R.string.entrando), Toast.LENGTH_SHORT).show()
    }

    fun mostraBotao() {
        btCadastro.visibility = View.VISIBLE
    }
}
