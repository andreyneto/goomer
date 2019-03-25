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
import br.com.andreyneto.goomer.databinding.FragmentRestaurantListBinding
import br.com.andreyneto.goomer.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class RestaurantListFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantListBinding
    private lateinit var viewModel: AppViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_list, container, false)

        binding.restaurantList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory(activity)).get(AppViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage ->
            if (errorMessage != null) {
                showError(errorMessage)
            } else {
                hideError()
            }
        })
        viewModel.restaurantListAdapter.setOnClickListener {
        }
        binding.viewModel = viewModel
        return binding.root
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
