package com.example.weatherappassignment.ui.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappassignment.ui.adapter.BindableAdapter

@BindingAdapter("recyclerData")
@Suppress("UNCHECKED_CAST")
fun <T> setRecyclerData(
    recyclerView: RecyclerView,
    recyclerData: T?
) {
    recyclerData?.let {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(it)
        }
    }
}
