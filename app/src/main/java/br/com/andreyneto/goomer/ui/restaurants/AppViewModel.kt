package br.com.andreyneto.goomer.ui.restaurants

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.base.BaseViewModel
import br.com.andreyneto.goomer.model.AppDao
import br.com.andreyneto.goomer.model.Restaurantes
import br.com.andreyneto.goomer.network.AppApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppViewModel(private val appDao: AppDao) : BaseViewModel() {

    @Inject
    lateinit var appApi: AppApi

    private lateinit var subscription: Disposable

    val loaderVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val restaurantListAdapter: RestaurantListAdapter = RestaurantListAdapter()

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { appDao.all }
            .concatMap { dbPostList ->
                if (dbPostList.isEmpty())
                    appApi.getRestaurants().concatMap { apiPostList
                        -> appDao.insertAll(apiPostList)
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveProductListStart() }
            .doOnTerminate { onRetrieveProductListFinish() }
            .doOnError { error -> onRetrieveProductListError(error) }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { error -> onRetrieveProductListError(error) }
            )
    }

    private fun onRetrieveProductListStart() {
        loaderVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveProductListFinish(){
        loaderVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Restaurantes>) {
        restaurantListAdapter.updateProductList(postList)
    }

    private fun onRetrieveProductListError(error: Throwable) {
        errorMessage.value = R.string.products_error
        Log.e("API ERROR", error.localizedMessage)
    }

}