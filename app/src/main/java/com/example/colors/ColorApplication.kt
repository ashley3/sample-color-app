package com.example.colors

import android.app.Application
import com.example.colors.database.ColorDatabase

class ColorApplication : Application() {
  val database: ColorDatabase by lazy { ColorDatabase.getInstance(this) }}