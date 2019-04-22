package br.com.andreyneto.goomer.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.goomer.R
import com.bumptech.glide.Glide

@BindingAdapter("mutableVisibility")
fun View.setMutableVisibility(visibility: Int?) {
    this.visibility = visibility ?: View.VISIBLE
}

@BindingAdapter("mutableText")
fun TextView.setMutableText(mutableText: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = getParentActivity()
    if (parentActivity != null && mutableText != null) {
        mutableText.observe(parentActivity, Observer { value -> text = value ?: "" })
    }
}

@BindingAdapter("price")
fun TextView.setPrice(mutableText: MutableLiveData<Double>?) {
    val parentActivity: AppCompatActivity? = getParentActivity()
    if (parentActivity != null && mutableText != null) {
        mutableText.observe(parentActivity, Observer { value -> text = String.format("R$%.2f", value) })
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(mutableText: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = getParentActivity()
    if (parentActivity != null && mutableText != null) {
        mutableText.observe(parentActivity, Observer { value ->
            Glide.with(context).load(value?:this.context.getDrawable(R.drawable.ic_launcher_foreground)).into(this)
        })
    }
}

@BindingAdapter("adapter")
fun RecyclerView.setAdapter(newAdapter: RecyclerView.Adapter<*>) {
    adapter = newAdapter
}