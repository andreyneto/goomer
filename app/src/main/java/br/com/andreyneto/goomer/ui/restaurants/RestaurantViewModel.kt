package br.com.andreyneto.goomer.ui.restaurants

import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.goomer.base.BaseViewModel
import br.com.andreyneto.goomer.model.Restaurantes
import br.com.andreyneto.goomer.utils.HoursUtil

class RestaurantViewModel : BaseViewModel() {
    private val restaurantName = MutableLiveData<String>()
    private val restaurantImage = MutableLiveData<String>()
    private val restauranteOpenStatus = MutableLiveData<String>()
    private lateinit var productClick: () -> Unit

    fun bind(restaurant: Restaurantes, function: () -> Unit) {
        restaurantName.value = restaurant.name
        restaurantImage.value = restaurant.image
        restauranteOpenStatus.value = HoursUtil.openStatus(restaurant)
        productClick = function
    }

    fun getRestaurantName(): MutableLiveData<String> = restaurantName

    fun getRestaurantImage(): MutableLiveData<String> = restaurantImage

    fun getRestaurantOpenStatus(): MutableLiveData<String> = restauranteOpenStatus

    fun itemClick() = productClick.invoke()
}