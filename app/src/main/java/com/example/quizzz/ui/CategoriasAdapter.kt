package com.example.quizzz.ui

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.ui.CategoriasAdapter.CategoriaViewHolder
import kotlinx.android.synthetic.main.categorias_adapter.view.*



class CategoriasAdapter (private var categorias: List<Categoria>, private  var listener: CategoriaListListener) :
    RecyclerView.Adapter<CategoriaViewHolder>() {
    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.preencherView(categorias[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.categorias_adapter, parent, false)
        )

    override fun getItemCount() = categorias.size

    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun preencherView(categoria: Categoria) {
            itemView.txtCategoria.text = categoria.nome

            itemView.cardCategoria.setOnClickListener {
                itemView.cardCategoria.setCardBackgroundColor(Color.LTGRAY)
                listener.preferences(categoria)
            }
        }
    }
}
