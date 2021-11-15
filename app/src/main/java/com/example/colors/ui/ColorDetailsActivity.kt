package com.example.colors.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.colors.ColorApplication
import com.example.colors.R
import com.example.colors.viewmodels.ColorViewModel
import com.example.colors.viewmodels.ColorViewModelFactory
import kotlinx.coroutines.launch

class ColorDetailsActivity : AppCompatActivity() {
  companion object {
    const val color_key = "COLOR"
  }
  private lateinit var viewModel : ColorViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_color_details)

    viewModel = ViewModelProvider(
      this, ColorViewModelFactory((application as ColorApplication).database.colorDao()))
      .get(ColorViewModel::class.java)

    val colorId = intent.getIntExtra(color_key, 0)

    lifecycleScope.launch {
      val color = viewModel.getColorById(colorId)
      val textView = findViewById<TextView>(R.id.color_name)
      textView.text = color.name
      try {
        textView.setBackgroundColor(color.hex.toColorInt())
      } catch (e: Exception) {
        Log.d("Ashley", "Not a color " + e.toString())
      }
    }
  }
}