package br.com.andreyneto.goomer.model

data class Sale(
    val description: String,
    val hours: List<Hour>,
    val price: Double
)