package br.com.andreyneto.goomer.ui.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.databinding.ItemRestaurantBinding
import br.com.andreyneto.goomer.model.Restaurantes

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    private lateinit var restaurantList: List<Restaurantes>
    private lateinit var listener: (restaurantes: Restaurantes) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRestaurantBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_restaurant, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() =
        if (::restaurantList.isInitialized) restaurantList.size else 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(restaurantList[position], listener)

    fun updateRestaurantList(restaurantList: List<Restaurantes>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    fun setOnClickListener(function: (restaurant: Restaurantes) -> Unit) {
        this.listener = function
    }

    class ViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RestaurantViewModel()

        fun bind(restaurant: Restaurantes, listener: (restaurant: Restaurantes) -> Unit) {
            viewModel.bind(restaurant) {
                listener.invoke(restaurant)
            }
            binding.viewModel = viewModel
        }
    }
}