package com.example.quizzz.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            btCadastro.setOnClickListener {
                if(txtSenha.equals(txtConfirma)){
                    cadastraUsuario()
                }
            }
    }

    private fun cadastraUsuario(){
        val nome = txtNome.text.toString()
        val email = txtEmail.text.toString()
        val Senha = txtSenha.text.toString()

            //colocar essas informações no banco

    }

}
