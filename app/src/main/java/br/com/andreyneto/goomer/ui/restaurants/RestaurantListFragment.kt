package br.com.andreyneto.goomer.ui.restaurants

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
import br.com.andreyneto.goomer.databinding.FragmentRestaurantListBinding
import br.com.andreyneto.goomer.injection.ViewModelFactory
import br.com.andreyneto.goomer.ui.AppViewModel
import br.com.andreyneto.goomer.ui.menu.MenuFragment

class RestaurantListFragment : BaseFragment() {

    private lateinit var binding: FragmentRestaurantListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_list, container, false)

        binding.restaurantList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory(activity)).get(AppViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage ->
            if (errorMessage != null) {
                showError(binding.root, errorMessage)
            } else {
                hideError()
            }
        })
        viewModel.restaurantListAdapter.setOnClickListener {
            (activity as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.frag_container, MenuFragment.get(it))
                .addToBackStack(null)
                .commit()
        }
        binding.viewModel = viewModel
        return binding.root
    }
}
