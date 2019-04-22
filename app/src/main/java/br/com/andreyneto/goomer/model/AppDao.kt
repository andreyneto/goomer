package br.com.andreyneto.goomer.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    @Insert
    fun insertRestaurant(restaurantes: List<Restaurantes>)

    @Insert
    fun insertMenuItens(menuItens: List<Menu>)

    @get:Query("SELECT * FROM restaurantes")
    val allRestaurants: List<Restaurantes>

    @Query("SELECT * FROM menu WHERE restaurantId = :restaurantId")
    fun allMenuItens(restaurantId: Int): List<Menu>

    @Query("DELETE FROM restaurantes")
    fun clearRestaurants()

    @Query("DELETE FROM menu")
    fun clearMenus()
}