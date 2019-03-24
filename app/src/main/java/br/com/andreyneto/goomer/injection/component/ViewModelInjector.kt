package br.com.andreyneto.goomer.injection.component

import br.com.andreyneto.goomer.injection.module.NetworkModule
import br.com.andreyneto.goomer.ui.restaurants.AppViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(appViewModel: AppViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}