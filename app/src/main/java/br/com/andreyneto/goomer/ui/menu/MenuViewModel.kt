package br.com.andreyneto.goomer.ui.menu

import android.view.View
import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.goomer.base.BaseViewModel
import br.com.andreyneto.goomer.model.Menu
import br.com.andreyneto.goomer.utils.HoursUtil

class MenuViewModel : BaseViewModel() {
    private val menuName = MutableLiveData<String>()
    private val menuGroup = MutableLiveData<String>()
    private val menuImage = MutableLiveData<String>()
    private val menuPrice = MutableLiveData<Double>()
    private var menuOldPrice = MutableLiveData<Double>()

    fun bind(menu: Menu) {
        menuName.value = menu.name
        menuGroup.value = menu.group
        menuImage.value = menu.image
        HoursUtil.currentSale(menu)?.let { sale ->
            menuOldPrice.value = menu.price
            menuPrice.value = sale.price
            return
        }
        menuPrice.value = menu.price
    }

    fun getMenuName(): MutableLiveData<String> = menuName

    fun getMenuGroup(): MutableLiveData<String> = menuGroup

    fun getMenuImage(): MutableLiveData<String> = menuImage

    fun getMenuPrice(): MutableLiveData<Double> = menuPrice

    fun getMenuOldPrice(): MutableLiveData<Double> = menuOldPrice

    fun hasPrice(): Int = if ((menuPrice.value?:0.0) > 0) View.VISIBLE else View.GONE

    fun hasSaleNow(): Int = if ((menuOldPrice.value?:0.0) > 0) View.VISIBLE else View.GONE
}