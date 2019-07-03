package com.example.quizzz.ui

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Pergunta
import kotlinx.android.synthetic.main.lista_activity.view.*
import kotlinx.android.synthetic.main.perguntas_adapter.view.*
import java.util.*
import android.widget.Toast
import com.example.quizzz.app.MainActivity
import com.ankushgrover.hourglass.Hourglass


class PerguntasAdapter (private var perguntas: List<Pergunta>, private  var listener: PerguntaListListener) :
    RecyclerView.Adapter<PerguntasAdapter.PerguntaViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val pergunta = perguntas[position]
        return if (pergunta.tipo == "multiple" )
            R.layout.perguntas_adapter
        else
            R.layout.perguntas_adapter2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PerguntaViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )

    override fun getItemCount() = perguntas.size

    override fun onBindViewHolder(holder: PerguntaViewHolder, position: Int) =
        holder.preencherView(perguntas[position])

    inner class PerguntaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi")
        fun preencherView(pergunta: Pergunta) {
            itemView.txtPergunta.text = pergunta.questao

            if (pergunta.tipo == "multiple") {
                var list: ArrayList<String> = ArrayList()

                list.add(pergunta.resposta_correta)
                list.add(pergunta.respostas_incorretas[0])
                list.add(pergunta.respostas_incorretas[1])
                list.add(pergunta.respostas_incorretas[2])

                list.shuffle()

                itemView.btResposta1.text = list[0]
                itemView.btResposta2.text = list[1]
                itemView.btResposta3.text = list[2]
                itemView.btResposta4.text = list[3]
            }
            else {
                var list = ArrayList<String>()

                list.add(pergunta.resposta_correta)
                list.add(pergunta.respostas_incorretas[0])

                list.shuffle()

                itemView.btResposta1.text = list[0]
                itemView.btResposta2.text = list[1]
            }

            if(pergunta.dificuldade == "easy") { //45 segundos
                object :CountDownTimer(45000, 1000){
                    override fun onTick(millisUntilFinished: Long) {
                        itemView.idTempo.text = itemView.resources.getString(R.string.tempo)+": ${millisUntilFinished/1000}"
                        if (pergunta.tipo == "multiple") {
                            itemView.btResposta1.setOnClickListener {
                                itemView.btResposta1.isClickable = false
                                itemView.btResposta2.isClickable = false
                                itemView.btResposta3.isClickable = false
                                itemView.btResposta4.isClickable = false

                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }

                            itemView.btResposta2.setOnClickListener {
                                itemView.btResposta1.isClickable = false
                                itemView.btResposta2.isClickable = false
                                itemView.btResposta3.isClickable = false
                                itemView.btResposta4.isClickable = false

                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }

                            itemView.btResposta3.setOnClickListener {
                                itemView.btResposta1.isClickable = false
                                itemView.btResposta2.isClickable = false
                                itemView.btResposta3.isClickable = false
                                itemView.btResposta4.isClickable = false

                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }

                            itemView.btResposta4.setOnClickListener {
                                itemView.btResposta1.isClickable = false
                                itemView.btResposta2.isClickable = false
                                itemView.btResposta3.isClickable = false
                                itemView.btResposta4.isClickable = false

                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }
                        }

                        else{
                            itemView.btResposta1.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }

                            itemView.btResposta2.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }
                        }
                    }

                    override fun onFinish() {
                        itemView.btResposta1.isClickable = false
                        itemView.btResposta2.isClickable = false
                        itemView.btResposta3.isClickable = false
                        itemView.btResposta4.isClickable = false

                        listener.abrirPerguntaActivity(-10)
                        Log.e("Pon","-10")
                    }
                }.start()
            }
            else if(pergunta.dificuldade == "medium") { //30 segundos
                object :CountDownTimer(30000, 1000){
                    override fun onTick(millisUntilFinished: Long) {
                        itemView.idTempo.text = itemView.resources.getString(R.string.tempo)+": ${millisUntilFinished/1000}"
                        if (pergunta.tipo == "multiple") {
                            itemView.btResposta1.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }

                            itemView.btResposta2.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }

                            itemView.btResposta3.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }

                            itemView.btResposta4.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }
                        }

                        else{
                            itemView.btResposta1.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }

                            itemView.btResposta2.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }
                        }
                    }

                    override fun onFinish() {
                        itemView.btResposta1.isClickable = false
                        itemView.btResposta2.isClickable = false
                        itemView.btResposta3.isClickable = false
                        itemView.btResposta4.isClickable = false

                        listener.abrirPerguntaActivity(-10)
                    }
                }.start()
            }
            else {//15 segundos
                object :CountDownTimer(15000, 1000){
                    override fun onTick(millisUntilFinished: Long) {
                        itemView.idTempo.text = itemView.resources.getString(R.string.tempo)+": ${millisUntilFinished/1000}"
                        if (pergunta.tipo == "multiple") {
                            itemView.btResposta1.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }

                            itemView.btResposta2.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }

                            itemView.btResposta3.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }

                            itemView.btResposta4.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if (itemView.btResposta2.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else if(itemView.btResposta3.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }

                                else {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }
                        }

                        else{
                            itemView.btResposta1.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }

                            }

                            itemView.btResposta2.setOnClickListener {
                                if(itemView.btResposta1.text == pergunta.resposta_correta) {
                                    listener.abrirPerguntaActivity(-10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                }
                                else {
                                    listener.abrirPerguntaActivity(+10)
                                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
                                }
                            }
                        }
                    }

                    override fun onFinish() {
                        itemView.btResposta1.isClickable = false
                        itemView.btResposta2.isClickable = false
                        itemView.btResposta3.isClickable = false
                        itemView.btResposta4.isClickable = false

                        listener.abrirPerguntaActivity(-10)
                    }
                }.start()
            }
        }
    }
}