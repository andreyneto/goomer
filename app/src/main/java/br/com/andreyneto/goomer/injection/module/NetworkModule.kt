package br.com.andreyneto.goomer.injection.module

import br.com.andreyneto.goomer.network.AppApi
import br.com.andreyneto.goomer.utils.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides @Singleton @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }

    @Provides @Singleton @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            )
            .build()
    }
}