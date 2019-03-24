package br.com.andreyneto.goomer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val group: String,
    val image: String,
    val name: String,
    val price: Int,
    val restaurantId: Int,
    val sales: List<Sale>
)