package com.example.colors.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colors.ColorApplication
import com.example.colors.R
import com.example.colors.database.Color
import com.example.colors.viewmodels.ColorViewModel
import com.example.colors.viewmodels.ColorViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnColorClickListener {
  private lateinit var viewModel : ColorViewModel
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val recyclerView = findViewById<RecyclerView>(R.id.names_recyclerView)
    recyclerView.layoutManager = LinearLayoutManager(this)
    val adapter = ColorAdapter(this)
    recyclerView.adapter = adapter

    viewModel = ViewModelProvider(
      this, ColorViewModelFactory((application as ColorApplication).database.colorDao()))
      .get(ColorViewModel::class.java)

    lifecycleScope.launch {
      // adapter.submitList(viewModel.allColors())
      viewModel.allColors().collect {
        adapter.submitList(it)
      }
    }


    findViewById<Button>(R.id.add_button).setOnClickListener {
      val newColor = Color(_id = 0, hex = findViewById<EditText>(R.id.input_hex).text.toString(), name = findViewById<EditText>(
        R.id.input_name).text.toString())
      lifecycleScope.launch { viewModel.insertColor(newColor) }
    }
  }

  override fun onClick(color: Color) {
    startActivity(Intent(this, ColorDetailsActivity::class.java).apply{
      putExtra(ColorDetailsActivity.color_key, color._id)
    })
  }
}