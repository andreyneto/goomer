package br.com.andreyneto.goomer.network

import br.com.andreyneto.goomer.model.Restaurantes
import io.reactivex.Observable
import retrofit2.http.GET

interface AppApi {
    @GET("/restaurants")
    fun getRestaurants(): Observable<List<Restaurantes>>
}