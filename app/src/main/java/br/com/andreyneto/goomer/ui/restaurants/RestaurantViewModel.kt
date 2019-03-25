package br.com.andreyneto.goomer.ui.restaurants

import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.goomer.base.BaseViewModel
import br.com.andreyneto.goomer.model.Restaurantes

class RestaurantViewModel : BaseViewModel() {
    private val restaurantName = MutableLiveData<String>()
    private val restaurantImage = MutableLiveData<String>()
    private lateinit var productClick: () -> Unit

    fun bind(restaurant: Restaurantes, function: () -> Unit) {
        restaurantName.value = restaurant.name
        restaurantImage.value = restaurant.image
        productClick = function
    }
    fun getRestaurantName(): MutableLiveData<String> {
        return restaurantName
    }
    fun getRestaurantImage(): MutableLiveData<String> {
        return restaurantImage
    }
    fun itemClick() {
        productClick.invoke()
    }
}