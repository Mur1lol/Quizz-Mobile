package com.example.quizzz.app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.R.string.senha
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.entidades.CategoriaListaResponse
import com.example.quizzz.entidades.CategoriaResponse
import com.example.quizzz.entidades.LoginResponse
import com.example.quizzz.servicos.CategoriaService
import com.example.quizzz.servicos.ListaCategoriaService
import com.example.quizzz.servicos.LoginService
import com.example.quizzz.ui.CategoriasAdapter
import com.example.quizzz.ui.PerguntasAdapter
import kotlinx.android.synthetic.main.activity_config.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ConfigActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
//    var listCategoria= arrayOf("Random","General Knowledge","Books","Entertainment: Film", "Music","Entertainment: Musicals and Theatres",
//        "Entertainment: Television", "Entertainment: Video Games", "Entertainment: Board Games", "Science and Nature", "Science: Computers",
//        "Science: Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art", "Celebrities","Animals","Vehicles",
//        "Entertainment: Comics","Science: Gadgets","Entertainment: Japanese Anime and Manga", "Entertainment: Cartoon and Animations")

    lateinit var retrofit: Retrofit
    lateinit var service: ListaCategoriaService
    lateinit var serviceCategoria: CategoriaService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        configuraRetrofit()
        carregarLista()

        idCategoria.onItemSelectedListener = this

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa :ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategoria)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        idCategoria.setAdapter(aa)



        btJogar.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
                    startActivity(intent)
        }

        var adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item)
        idCategoria.adapter = adapter

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    fun configuraRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<ListaCategoriaService>(ListaCategoriaService::class.java)

    }

    fun carregarLista() {
        var list: List<Categoria>? = null

        service.getListaCategoria(list!!).enqueue(object: Callback<CategoriaListaResponse>{
            override fun onFailure(call: Call<CategoriaListaResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<CategoriaListaResponse>, response: Response<CategoriaListaResponse>) {
                val listaCategoria: CategoriaListaResponse? = response.body()
                if (listaCategoria != null){
                    var adapter = CategoriasAdapter(listaCategoria)
                    listCategoria.adapter = adapter
                    listCategoria.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            }

        })
    }
}
