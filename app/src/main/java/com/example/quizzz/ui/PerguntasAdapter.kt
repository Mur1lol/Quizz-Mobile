package com.example.quizzz.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

            //MUDAR DEPOIS

            if (pergunta.tipo == "multiple") {
                var colocacao = embaralhar(5, 1)


                itemView.btResposta1.text = pergunta.respostas_incorretas[0]
                itemView.btResposta2.text = pergunta.respostas_incorretas[1]
                itemView.btResposta3.text = pergunta.respostas_incorretas[2]
                itemView.btResposta4.text = pergunta.resposta_correta

                if (colocacao == 0) {
                    itemView.btResposta1.text = pergunta.resposta_correta
                    itemView.btResposta4.text = pergunta.respostas_incorretas[0]
                } else if (colocacao == 1) {
                    itemView.btResposta2.text = pergunta.resposta_correta
                    itemView.btResposta4.text = pergunta.respostas_incorretas[1]
                } else if (colocacao == 2) {
                    itemView.btResposta3.text = pergunta.resposta_correta
                    itemView.btResposta4.text = pergunta.respostas_incorretas[2]
                }
            }
            else{
                var local = embaralhar(3, 1)


                itemView.btResposta1.text = pergunta.respostas_incorretas[0]
                itemView.btResposta2.text = pergunta.resposta_correta

                if (local == 0) {
                    itemView.btResposta1.text = pergunta.resposta_correta
                    itemView.btResposta2.text = pergunta.respostas_incorretas[0]
                }
            }
        }
        fun embaralhar(max: Int, min: Int ): Int{
            val random = Random()
            return random.nextInt( max-min)
        }
    }


}
