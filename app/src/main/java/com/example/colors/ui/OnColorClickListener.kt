package com.example.colors.ui

import com.example.colors.database.Color

interface OnColorClickListener {
  fun onClick(color : Color)
}