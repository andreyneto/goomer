package br.com.andreyneto.goomer.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.base.BaseFragment
import br.com.andreyneto.goomer.injection.ViewModelFactory
import br.com.andreyneto.goomer.model.Restaurantes
import br.com.andreyneto.goomer.ui.AppViewModel
import br.com.andreyneto.goomer.utils.HoursUtil

class MenuFragment : BaseFragment() {

    private lateinit var restaurant: Restaurantes

    companion object {
        fun get(restaurant: Restaurantes): MenuFragment {
            val mf =  MenuFragment()
            mf.setRestaurant(restaurant)
            return mf
        }
    }

    private fun setRestaurant(restaurant: Restaurantes) {
        this.restaurant = restaurant
    }

    private lateinit var binding: br.com.andreyneto.goomer.databinding.FragmentMenuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        binding.menuList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory(activity)).get(AppViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage ->
            if (errorMessage != null) {
                showError(binding.root ,errorMessage)
            } else {
                hideError()
            }
        })
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.let {
            it.title = restaurant.name
            it.subtitle = HoursUtil.openStatus(restaurant)
        }
        viewModel.loadMenu(restaurant.id)
    }

}