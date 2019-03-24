package br.com.andreyneto.goomer.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {
    @Insert
    fun insertAll(restaurantes: List<Restaurantes>)

    @get:Query("SELECT * FROM restaurantes")
    val all: List<Restaurantes>
}