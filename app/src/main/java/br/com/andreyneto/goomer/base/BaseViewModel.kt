package br.com.andreyneto.goomer.base

import androidx.lifecycle.ViewModel
import br.com.andreyneto.goomer.injection.component.DaggerViewModelInjector
import br.com.andreyneto.goomer.injection.component.ViewModelInjector
import br.com.andreyneto.goomer.injection.module.NetworkModule
import br.com.andreyneto.goomer.ui.restaurants.AppViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector =
        DaggerViewModelInjector.builder().networkModule(NetworkModule).build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is AppViewModel -> injector.inject(this)
        }
    }
}