package br.com.andreyneto.goomer.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.base.BaseViewModel
import br.com.andreyneto.goomer.model.*
import br.com.andreyneto.goomer.network.AppApi
import br.com.andreyneto.goomer.ui.menu.MenuAdapter
import br.com.andreyneto.goomer.ui.restaurants.RestaurantListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class AppViewModel(private val appDao: AppDao) : BaseViewModel() {

    @Inject
    lateinit var appApi: AppApi

    private lateinit var subscription: Disposable

    private var lastError = -1
    private var lastRestaurant = -1

    val loaderVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        if (lastError==0) loadRestaurants()
        else if (lastError==1) loadMenu(lastRestaurant)
    }
    val restaurantListAdapter: RestaurantListAdapter = RestaurantListAdapter()
    val menuAdapter: MenuAdapter = MenuAdapter()

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    init {
        clearAll()
        loadRestaurants()
    }

    private fun loadRestaurants() {
        subscription = Observable.fromCallable { appDao.allRestaurants }
            .concatMap { dbRestaurantList ->
                if (dbRestaurantList.isEmpty())
                    appApi.getRestaurants().concatMap { apiRestaurantList ->
                        appDao.insertRestaurant(apiRestaurantList)
                        Observable.just(apiRestaurantList)
                    }
                else Observable.just(dbRestaurantList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .doOnError { error -> onRetrieveRestaurantListError(error) }
            .subscribe(
                { result -> onRetrieveRestaurantListSuccess(result) },
                { error -> onRetrieveRestaurantListError(error) }
            )
    }
    fun loadMenu(restaurantId: Int) {
        lastRestaurant = restaurantId
        subscription = Observable.fromCallable { appDao.allMenuItens(restaurantId)}
            .concatMap { dbMenuList ->
                if(dbMenuList.isEmpty())
                    appApi.getMenuItens(restaurantId).concatMap {apiMenuList ->
                        appDao.insertMenuItens(apiMenuList)
                        Observable.just(apiMenuList)
                    }
                else Observable.just(dbMenuList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .doOnError { error -> onRetrieveMenuError(error) }
            .subscribe(
                { result -> onRetrieveMenuListSuccess(result) },
                { error -> onRetrieveMenuError(error) }
            )
    }

    private fun clearAll() {
        doAsync {
            appDao.clearMenus()
            appDao.clearRestaurants()
        }
    }

    private fun onRetrieveStart() {
        Log.i("API RETRIEVE", "START")
        loaderVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveFinish(){
        Log.i("API RETRIEVE", "FINISH")
        loaderVisibility.value = View.GONE
    }

    private fun onRetrieveRestaurantListSuccess(restaurantsList: List<Restaurantes>) {
        Log.i("API SUCCESS::RESTAURANT", restaurantsList.toString())
        restaurantListAdapter.updateRestaurantList(restaurantsList)
    }

    private fun onRetrieveMenuListSuccess(menuList: List<Menu>) {
        Log.i("API SUCCESS :: MENU", menuList.toString())
        menuAdapter.updateMenuList(menuList)
    }

    private fun onRetrieveRestaurantListError(error: Throwable) {
        errorMessage.value = R.string.restaurants_error
        Log.e("API ERROR :: RESTAURANT", error.localizedMessage)
        error.printStackTrace()
    }

    private fun onRetrieveMenuError(error: Throwable) {
        errorMessage.value = R.string.products_error
        Log.e("API ERROR :: MENU", error.localizedMessage)
        error.printStackTrace()
    }

}