package com.example.quizzz.ui

import android.content.Intent
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Pergunta
import kotlinx.android.synthetic.main.perguntas_adapter.view.*
import java.util.*

class PerguntasAdapter (private var perguntas: List<Pergunta>) :
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
        fun preencherView(pergunta: Pergunta) {

            itemView.txtCategoria.text = pergunta.categoria
            itemView.txtTipo.text = pergunta.tipo
            itemView.txtDificuldade.text = pergunta.dificuldade
            itemView.txtPergunta.text = pergunta.questao

            object :CountDownTimer(30000, 1000){
                override fun onTick(millisUntilFinished: Long) {

                    itemView.idTempo.text = "tempo: ${millisUntilFinished/1000}"
                }

                override fun onFinish() {// QUANDO ACABAR O TEMPO

//                    val intent = Intent(this, rankingActivity::class.java)
//                    startActivity(intent)
                }

            }.start()


            //MUDAR DEPOIS - Algumas perguntas n funcionam

            if (pergunta.tipo == "multiple") {

                var list :ArrayList<String> = ArrayList()

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
            else{
                var list = ArrayList<String>()

                list.add(pergunta.resposta_correta)
                list.add(pergunta.respostas_incorretas[0])

                list.shuffle()

                itemView.btResposta1.text = list[0]
                itemView.btResposta2.text = list[1]

            }
        }
    }


}
