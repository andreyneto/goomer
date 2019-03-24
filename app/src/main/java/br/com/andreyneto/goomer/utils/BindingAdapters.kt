package br.com.andreyneto.goomer.utils

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("mutableVisibility")
fun View.setMutableVisibility(mutableVisibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = getParentActivity()
    if (parentActivity != null && mutableVisibility != null) {
        mutableVisibility.observe(parentActivity, Observer { value -> visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun TextView.setMutableText(mutableText: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = getParentActivity()
    if (parentActivity != null && mutableText != null) {
        mutableText.observe(parentActivity, Observer { value -> text = value ?: "" })
    }
}

@BindingAdapter("adapter")
fun RecyclerView.setAdapter(newAdapter: RecyclerView.Adapter<*>) {
    adapter = newAdapter
}