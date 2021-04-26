package pontinisystems.vespa.presenter.favorite_stocks.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import pontinisystems.vespa.databinding.FragmentFavoriteStocksBinding
import pontinisystems.vespa.di.appModule
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.presenter.favorite_stocks.ui.adapter.FavoriteStocksAdapter
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewaction.FavoriteStocksAction
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate.FavoriteStocksViewState
import pontinisystems.vespa.presenter.favorite_stocks.viewmodel.FavoriteStocksViewModel
import pontinisystems.vespa.presenter.select_new_favorite_stock.SelectNewFavoriteStockActivity
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction.SelectNewFavoriteStockAction

class FavoriteStocksFragment : Fragment() {

    private val viewModel: FavoriteStocksViewModel by viewModel()
    private fun inject() = loadModules
    private lateinit var binding:FragmentFavoriteStocksBinding

    private val adapter by lazy { FavoriteStocksAdapter(viewModel, viewModel) }

    private val loadModules by lazy {
        unloadKoinModules(appModule)
        loadKoinModules(appModule)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentFavoriteStocksBinding.inflate(inflater,container,false).apply{

        setupView()
        observeChanges()
    }.root

    private fun FragmentFavoriteStocksBinding.setupView() {

        binding=this
        lifecycleOwner=viewLifecycleOwner
        favoriteStocksViewModel=viewModel
        viewState=viewModel.viewState

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInitialData()



    }
    private fun setupInitialData() {
        binding.myRecyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            startActivity(Intent(this.activity, SelectNewFavoriteStockActivity::class.java))
        }
        viewModel.dispatchViewAction(FavoriteStocksAction.FetchFavoritesStocks)
    }

    private fun observeChanges() {
        viewModel.viewState.action.asLiveData().observe(viewLifecycleOwner, Observer { action->
            when(action){
                is FavoriteStocksViewState.Action.SetFavoriteStocksList->setListAdapter(action.list)
            }
        })
    }
    private fun setListAdapter(list: List<StockFavoriteUi>) {
        binding.myRecyclerView.adapter?.let {
            adapter.submitList(list)
            it.notifyDataSetChanged()
        }
        Log.i("SIZE ADAPTER","SIZE ADAPTER "+
                adapter.currentList.size)
    }

    override fun onStop() {
        viewModel.onStop()
        super.onStop()
    }

    override fun onResume() {
        viewModel.dispatchViewAction(FavoriteStocksAction.FetchFavoritesStocks)
        super.onResume()
    }

}