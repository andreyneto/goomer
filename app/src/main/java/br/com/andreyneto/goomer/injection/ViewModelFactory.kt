package br.com.andreyneto.goomer.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import br.com.andreyneto.goomer.model.AppDatabase
import br.com.andreyneto.goomer.ui.AppViewModel

class ViewModelFactory(private val activity: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        activity?.let {
            if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
                val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "restaurants")
                    .build()
                @Suppress("UNCHECKED_CAST")
                return AppViewModel(db.appDao()) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModelclass")
    }
}