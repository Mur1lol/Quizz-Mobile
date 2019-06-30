package com.example.quizzz.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.entidades.CategoriaResponse
import com.example.quizzz.ui.CategoriasAdapter.CategoriaViewHolder
import kotlinx.android.synthetic.main.activity_config.view.*

class CategoriasAdapter (private var categorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriaViewHolder>() {
    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.preencherView(categorias[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_config, parent, false)
        )

    override fun getItemCount() = categorias.size

    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(categoria: Categoria) {
            categoria.nome
        }
    }
}
