package com.example.quizzz.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Pergunta
import com.example.quizzz.entidades.Ranking
import kotlinx.android.synthetic.main.ranking_adapter.view.*

class RankingAdapter (private var lista: List<Ranking>) :
    RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RankingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ranking_adapter, parent, false)
        )

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) =
        holder.preencherView(lista[position])

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(ranking: Ranking) {
            itemView.txtNick.text = ranking.nome
            itemView.txtPonto.text = ""+ranking.pontuacao
            itemView.txtData.text = ""+ranking.ultimaPartida
        }
    }
}