package br.com.andreyneto.goomer.ui.menu

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.databinding.ItemMenuBinding
import br.com.andreyneto.goomer.model.Menu

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private lateinit var menuList: List<Menu>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val binding: ItemMenuBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_menu, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = if(::menuList.isInitialized) menuList.size else 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

    fun updateMenuList(menuList: List<Menu>) {
        this.menuList = menuList
        notifyDataSetChanged()
        Handler().postDelayed({
            notifyDataSetChanged()
        }, 60000)
    }

    class ViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MenuViewModel()
        fun bind(menu: Menu) {
            viewModel.bind(menu)
            binding.viewModel = viewModel
        }
    }
}