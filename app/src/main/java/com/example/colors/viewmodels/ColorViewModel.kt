package com.example.colors.viewmodels

import androidx.lifecycle.ViewModel
import com.example.colors.database.Color
import com.example.colors.database.ColorDao
import kotlinx.coroutines.flow.Flow

class ColorViewModel(private val colorDao : ColorDao) : ViewModel() {
  fun allColors() : Flow<List<Color>> = colorDao.getAll()
  suspend fun getColorById(id : Int) : Color = colorDao.getColorById(id)
  suspend fun insertColor(color : Color) = colorDao.insert(color)
}