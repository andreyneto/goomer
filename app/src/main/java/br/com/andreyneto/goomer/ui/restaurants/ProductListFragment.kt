package br.com.andreyneto.goomer.ui.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.databinding.FragmentProductListBinding
import br.com.andreyneto.goomer.injection.ViewModelFactory
import br.com.andreyneto.goomer.model.CartItem
import br.com.andreyneto.goomer.model.Product
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.selector

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: AppViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)

        binding.productList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory(activity)).get(AppViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage ->
            if (errorMessage != null) {
                showError(errorMessage)
            } else {
                hideError()
            }
        })
        viewModel.cart.observe(this, Observer {
            binding.root.snackbar("${it.last().name} adicionado ao pedido")
        })
        viewModel.restaurantListAdapter.setOnClickListener {
            if(it.items.size > 1) {
                selector(it.name, it.items) { dialogInterface, i ->
                    addToCart(it, i)
                }
                return@setOnClickListener
            }
            addToCart(it, 0)
        }
        binding.viewModel = viewModel
        return binding.root
    }

    private fun addToCart(product: Product, item: Int) {
        with(binding) {
            val cartItem = CartItem("${product.name} - ${product.items[item]}", product.price)
            viewModel?.let {
                val list = (it.cart.value ?: emptyList<CartItem>()).toMutableList()
                list.add(cartItem)
                it.cart.postValue(list)
            }
        }
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(
            binding.root,
            errorMessage, Snackbar.LENGTH_INDEFINITE
        )
        errorSnackbar?.setAction(
            R.string.retry,
            viewModel.errorClickListener
        )
        errorSnackbar?.show()
    }
    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
