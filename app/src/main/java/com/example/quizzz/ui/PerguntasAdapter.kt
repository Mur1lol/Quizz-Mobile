package com.example.quizzz.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Pergunta
import kotlinx.android.synthetic.main.perguntas_adapter.view.*
import java.util.*
import androidx.annotation.RequiresApi


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
            itemView.txtCategoria.text = pergunta.categoria
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
            } else {
                var list = ArrayList<String>()

                list.add(pergunta.resposta_correta)
                list.add(pergunta.respostas_incorretas[0])

                list.shuffle()

                itemView.btResposta1.text = list[0]
                itemView.btResposta2.text = list[1]
            }

            var tempo: Long = 1000
            var pontos  = 0
            var maisTarde = 0

            if (pergunta.dificuldade == "easy") {
                tempo = 45000
                pontos = 5
                maisTarde = -2
            }
            if (pergunta.dificuldade == "medium") {
                tempo = 30000
                pontos = 8
                maisTarde = -4
            }
            if (pergunta.dificuldade == "hard") {
                tempo = 15000
                pontos = 10
                maisTarde = -6
            }


            var start: CountDownTimer = object : CountDownTimer(tempo, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    itemView.idTempo.text = itemView.resources.getString(R.string.tempo) + ": ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    if (pergunta.tipo == "multiple") {
                        itemView.btResposta1.isClickable = false
                        itemView.btResposta2.isClickable = false
                        itemView.btResposta3.isClickable = false
                        itemView.btResposta4.isClickable = false
                    }
                    else {
                        itemView.btResposta1.isClickable = false
                        itemView.btResposta2.isClickable = false
                    }

                    listener.abrirPerguntaActivity(pontos*-1)

                }
            }.start()

            if (pergunta.tipo == "multiple") {
                itemView.btResposta1.setOnClickListener {
                    itemView.btResposta1.isClickable = false
                    itemView.btResposta2.isClickable = false
                    itemView.btResposta3.isClickable = false
                    itemView.btResposta4.isClickable = false

                    start.cancel()

                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)


                    mudaCor(itemView.btResposta1, pergunta)
                    mudaCor(itemView.btResposta2, pergunta)
                    mudaCor(itemView.btResposta3, pergunta)
                    mudaCor(itemView.btResposta4, pergunta)

                    if(itemView.btResposta1.text == pergunta.resposta_correta){ listener.abrirPerguntaActivity(pontos)}
                    else{ listener.abrirPerguntaActivity(pontos*-1)}

                }

                itemView.btResposta2.setOnClickListener {

                    itemView.btResposta1.isClickable = false
                    itemView.btResposta2.isClickable = false
                    itemView.btResposta3.isClickable = false
                    itemView.btResposta4.isClickable = false

                    start.cancel()

                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)

                    mudaCor(itemView.btResposta1, pergunta)
                    mudaCor(itemView.btResposta2, pergunta)
                    mudaCor(itemView.btResposta3, pergunta)
                    mudaCor(itemView.btResposta4, pergunta)

                    if(itemView.btResposta2.text == pergunta.resposta_correta){ listener.abrirPerguntaActivity(pontos)}
                    else{ listener.abrirPerguntaActivity(pontos*-1)}

                }

                itemView.btResposta3.setOnClickListener {

                    itemView.btResposta1.isClickable = false
                    itemView.btResposta2.isClickable = false
                    itemView.btResposta3.isClickable = false
                    itemView.btResposta4.isClickable = false

                    start.cancel()

                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)

                    mudaCor(itemView.btResposta1, pergunta)
                    mudaCor(itemView.btResposta2, pergunta)
                    mudaCor(itemView.btResposta3, pergunta)
                    mudaCor(itemView.btResposta4, pergunta)

                    if(itemView.btResposta3.text == pergunta.resposta_correta){ listener.abrirPerguntaActivity(pontos)}
                    else{ listener.abrirPerguntaActivity(pontos*-1)}

                }

                itemView.btResposta4.setOnClickListener {

                    itemView.btResposta1.isClickable = false
                    itemView.btResposta2.isClickable = false
                    itemView.btResposta3.isClickable = false
                    itemView.btResposta4.isClickable = false

                    start.cancel()

                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta3.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta4.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)

                    mudaCor(itemView.btResposta1, pergunta)
                    mudaCor(itemView.btResposta2, pergunta)
                    mudaCor(itemView.btResposta3, pergunta)
                    mudaCor(itemView.btResposta4, pergunta)

                    if(itemView.btResposta4.text == pergunta.resposta_correta){ listener.abrirPerguntaActivity(pontos)}
                    else{ listener.abrirPerguntaActivity(pontos*-1)}

                }
            } else {
                itemView.btResposta1.setOnClickListener {
                    itemView.btResposta1.isClickable = false
                    itemView.btResposta2.isClickable = false

                    start.cancel()

                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)

                    mudaCor(itemView.btResposta1, pergunta)
                    mudaCor(itemView.btResposta2, pergunta)

                    if(itemView.btResposta1.text == pergunta.resposta_correta){ listener.abrirPerguntaActivity(pontos)}
                    else{ listener.abrirPerguntaActivity(pontos*-1)}

                }

                itemView.btResposta2.setOnClickListener {

                    itemView.btResposta1.isClickable = false
                    itemView.btResposta2.isClickable = false

                    start.cancel()

                    itemView.btResposta1.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)
                    itemView.btResposta2.background = itemView.resources.getDrawable(R.drawable.button_border_vermelho)

                    mudaCor(itemView.btResposta1, pergunta)
                    mudaCor(itemView.btResposta2, pergunta)

                    if(itemView.btResposta2.text == pergunta.resposta_correta){ listener.abrirPerguntaActivity(pontos)}
                    else{ listener.abrirPerguntaActivity(pontos*-1)}

                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        private fun mudaCor(botao: Button?, pergunta: Pergunta) {
            if(botao!!.text == pergunta.resposta_correta){
                botao.background = itemView.resources.getDrawable(R.drawable.button_border_verde)
            }
        }



    }
}
