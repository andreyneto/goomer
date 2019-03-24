package br.com.andreyneto.goomer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurantes(
    val address: String,
    val hours: List<Hour>,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String
)