package pontinisystems.vespa.presenter.products.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pontinisystems.vespa.coreapp.ActionDispatcher
import pontinisystems.vespa.databinding.ProductViewHolderBinding
import pontinisystems.vespa.domain.entities.ProductUi
import pontinisystems.vespa.presenter.products.ui.viewaction.ItemProductAction
import pontinisystems.vespa.presenter.products.viewmodel.ProductsViewModel


class ProductsAdapter(
    private val viewModel: ProductsViewModel,
    private val dispactcher: ActionDispatcher<ItemProductAction>
) : ListAdapter<ProductUi, RecyclerView.ViewHolder>(FavoriteStocksDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StockViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StockViewHolder).bind(getItem(position), viewModel)
    }


}


class StockViewHolder private constructor(private val binding: ProductViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        ProductViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun bind(data: ProductUi, viewModel: ProductsViewModel) {
        binding.itemData = data
    }


}


object FavoriteStocksDiffCallback : DiffUtil.ItemCallback<ProductUi>() {
    override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
        return false
    }

}