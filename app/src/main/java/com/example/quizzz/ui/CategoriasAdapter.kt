package com.example.quizzz.ui

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.ui.CategoriasAdapter.CategoriaViewHolder
import kotlinx.android.synthetic.main.perguntas_adapter.view.*
import java.util.*

class CategoriasAdapter (private var categorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriaViewHolder>() {
    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.preencherView(categorias[position])
    }

    override fun getItemViewType(position: Int): Int {
        val categoria = categorias[position]
            R.layout.activity_config
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriaViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )

    override fun getItemCount() = categorias.size

    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(categoria: Categoria) {



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
