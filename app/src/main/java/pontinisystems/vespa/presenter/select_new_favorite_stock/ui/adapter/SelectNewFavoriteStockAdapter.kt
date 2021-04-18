package pontinisystems.vespa.presenter.select_new_favorite_stock.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pontinisystems.vespa.coreapp.ActionDispatcher
import pontinisystems.vespa.databinding.SelectNewFavoriteStockViewHolderBinding
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction.SelectNewFavoriteStockAction
import pontinisystems.vespa.presenter.select_new_favorite_stock.viewmodel.SelectNewFavoriteStockViewModel


class SelectNewFavoriteStockAdapter(
    private val viewModel: SelectNewFavoriteStockViewModel,
    private val dispactcher: ActionDispatcher<SelectNewFavoriteStockAction>
) : ListAdapter<OptionStockFavoriteUi, RecyclerView.ViewHolder>(SelectNewFavoriteStockDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SelectNewStockViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SelectNewStockViewHolder).bind(getItem(position), viewModel)
    }


}


class SelectNewStockViewHolder private constructor(private val binding: SelectNewFavoriteStockViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    constructor(parent: ViewGroup) : this(
        SelectNewFavoriteStockViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    fun bind(data: OptionStockFavoriteUi, viewModel: SelectNewFavoriteStockViewModel) {
        binding.itemData = data
        binding.clContainer.setOnClickListener {
            binding.checkbox.isChecked = binding.checkbox.isChecked.not()
            if (data.checked) {
                viewModel.dispatchViewAction(SelectNewFavoriteStockAction.RemoveFavoriteStock(data))

            } else {
                viewModel.dispatchViewAction(SelectNewFavoriteStockAction.SelectNewFavoriteStock(data))

            }

        }

    }


}


object SelectNewFavoriteStockDiffCallback : DiffUtil.ItemCallback<OptionStockFavoriteUi>() {
    override fun areItemsTheSame(oldItem: OptionStockFavoriteUi, newItem: OptionStockFavoriteUi): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: OptionStockFavoriteUi, newItem: OptionStockFavoriteUi): Boolean {
        return false
    }

}