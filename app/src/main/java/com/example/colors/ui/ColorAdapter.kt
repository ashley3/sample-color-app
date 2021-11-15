package com.example.colors.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.colors.R
import com.example.colors.database.Color

class ColorAdapter (private var onClickListener : OnColorClickListener) : ListAdapter<Color, ColorAdapter.ColorViewHolder>(
  DiffCallback) {
  class ColorViewHolder(myView : View) : RecyclerView.ViewHolder(myView) {
    val textView : TextView = myView.findViewById(R.id.text_view)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ColorViewHolder {
    val holder = ColorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.color_item, parent, false))
    holder.textView.setOnClickListener{
      val pos = holder.adapterPosition
      onClickListener.onClick(getItem(pos))
    }
    return holder
  }

  override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
    holder.textView.text = getItem(position).name
    try {
      holder.textView.setBackgroundColor((getItem(position).hex).toColorInt())
    } catch (e: Exception) {
      Log.d("Ashley", "Not a color " + e.toString())
    }
  }

  companion object {
    private val DiffCallback = object : DiffUtil.ItemCallback<Color>() {
      override fun areItemsTheSame(oldItem: Color, newItem: Color): Boolean {
        return oldItem._id == newItem._id
      }

      override fun areContentsTheSame(oldItem: Color, newItem: Color): Boolean {
        return oldItem == newItem
      }
    }
  }
}