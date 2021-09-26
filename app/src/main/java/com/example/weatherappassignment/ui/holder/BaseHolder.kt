package com.example.weatherappassignment.ui.holder

import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}