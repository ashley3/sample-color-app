package com.example.colors.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.colors.database.ColorDao

class ColorViewModelFactory(
  private val colorDao: ColorDao
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(ColorViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return ColorViewModel(colorDao) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}