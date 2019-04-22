package br.com.andreyneto.goomer.network

import br.com.andreyneto.goomer.model.Menu
import br.com.andreyneto.goomer.model.Restaurantes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("/restaurants")
    fun getRestaurants(): Observable<List<Restaurantes>>

    @GET("/restaurants/{restauranteId}/menu")
    fun getMenuItens(@Path("restauranteId") restauranteId: Int): Observable<List<Menu>>
}