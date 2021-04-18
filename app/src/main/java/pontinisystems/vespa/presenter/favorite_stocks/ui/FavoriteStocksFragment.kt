package pontinisystems.vespa.presenter.favorite_stocks.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import pontinisystems.vespa.R
import pontinisystems.vespa.di.appModule
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.adapter.SelectNewFavoriteStockAdapter
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction.SelectNewFavoriteStockAction
import pontinisystems.vespa.presenter.favorite_stocks.viewmodel.FavoriteStocksViewModel
import pontinisystems.vespa.presenter.select_new_favorite_stock.SelectNewFavoriteStockActivity
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.SelectNewFavoriteStocFragment

class FavoriteStocksFragment : Fragment() {

    private val ViewModel: FavoriteStocksViewModel by viewModel()
    private fun inject() = loadModules
    //private val adapter by lazy { SelectNewFavoriteStockAdapter(ViewModel,viewModel) }
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
    ): View? {
        return inflater.inflate(R.layout.favorite_stocks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModel.dispatchViewAction(SelectNewFavoriteStockAction.FetchFavoriteStock)
        startActivity(Intent(this.activity, SelectNewFavoriteStockActivity::class.java))

    }
}