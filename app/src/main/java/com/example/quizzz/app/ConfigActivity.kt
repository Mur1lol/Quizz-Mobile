package com.example.quizzz.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.quizzz.R

class ConfigActivity : AppCompatActivity() {

    lateinit var spinner :Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

//        var adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item)
//        spinner.adapter = adapter

    }
}
