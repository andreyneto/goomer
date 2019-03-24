package br.com.andreyneto.goomer.ui.restaurants

import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.goomer.base.BaseViewModel
import br.com.andreyneto.goomer.model.Product

class ProductViewModel : BaseViewModel() {
    private val productName = MutableLiveData<String>()
    private val productDetail = MutableLiveData<String>()
    private val productPrice = MutableLiveData<String>()
    private lateinit var productClick: () -> Unit

    fun bind(product: Product, function: () -> Unit) {
        productName.value = product.name
        productDetail.value = product.detail
        productPrice.value = String.format("R$ %.${2}f", product.price)
        productClick = function
    }
    fun getProductName(): MutableLiveData<String> {
        return productName
    }
    fun getProductDetail(): MutableLiveData<String> {
        return productDetail
    }
    fun getProductPrice(): MutableLiveData<String> {
        return productPrice
    }
    fun itemClick() {
        productClick.invoke()
    }
}