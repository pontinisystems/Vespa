package pontinisystems.vespa.presenter.favorite_stocks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pontinisystems.vespa.coreapp.ActionDispatcher
import pontinisystems.vespa.databinding.FavoriteStockViewHolderBinding
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewaction.FavoriteStocksAction
import pontinisystems.vespa.presenter.favorite_stocks.viewmodel.FavoriteStocksViewModel


class FavoriteStocksAdapter(
    private val viewModel: FavoriteStocksViewModel,
    private val dispactcher: ActionDispatcher<FavoriteStocksAction>
) : ListAdapter<StockFavoriteUi, RecyclerView.ViewHolder>(FavoriteStocksDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StockViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StockViewHolder).bind(getItem(position), viewModel)
    }


}


class StockViewHolder private constructor(private val binding: FavoriteStockViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        FavoriteStockViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun bind(data: StockFavoriteUi, viewModel: FavoriteStocksViewModel) {
        binding.itemData = data
    }


}


object FavoriteStocksDiffCallback : DiffUtil.ItemCallback<StockFavoriteUi>() {
    override fun areItemsTheSame(oldItem: StockFavoriteUi, newItem: StockFavoriteUi): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: StockFavoriteUi, newItem: StockFavoriteUi): Boolean {
        return false
    }

}