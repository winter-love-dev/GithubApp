package com.season.winter.core.common.bindingAdapter

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("bindQueryText")
fun bindQueryTextListener(searchView: SearchView, query: (String?) -> Unit) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
            query(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }
    })
}