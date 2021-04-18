package pontinisystems.vespa.presenter.select_new_favorite_stock.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import pontinisystems.vespa.databinding.FragmentSelectNewFavoriteStockBinding
import pontinisystems.vespa.di.appModule
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.adapter.SelectNewFavoriteStockAdapter
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction.SelectNewFavoriteStockAction
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewstate.SelectNewFavoriteStockViewState
import pontinisystems.vespa.presenter.select_new_favorite_stock.viewmodel.SelectNewFavoriteStockViewModel


class SelectNewFavoriteStocFragment : Fragment(),  SearchView.OnQueryTextListener  {

    private val viewModel: SelectNewFavoriteStockViewModel by viewModel()
    private fun inject() = loadModules
    private lateinit var binding:FragmentSelectNewFavoriteStockBinding

    private val adapter by lazy { SelectNewFavoriteStockAdapter(viewModel,viewModel) }
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
    )= FragmentSelectNewFavoriteStockBinding.inflate(inflater,container,false).apply{

        setupView()
        observeChanges()
    }.root

    private fun observeChanges() {
        viewModel.viewState.action.observe(viewLifecycleOwner, Observer { action->
            when(action){
                is SelectNewFavoriteStockViewState.Action.SetNewFavoriteStocksList->setListAdapter(action.list)
            }
        })
    }

    private fun setListAdapter(list: List<OptionStockFavoriteUi>) {
       binding.myRecyclerView.adapter?.let {
           adapter.submitList(list)
           it.notifyDataSetChanged()
       }
        Log.i("SIZE ADAPTER","SIZE ADAPTER "+
                adapter.currentList.size)
    }

    private fun FragmentSelectNewFavoriteStockBinding.setupView() {

       binding=this
        lifecycleOwner=viewLifecycleOwner
        selectNewFavoriteStockViewModel=viewModel
        viewState=viewModel.viewState

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInitialData()
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.dispatchViewAction(SelectNewFavoriteStockAction.SearchStock(newText))
        return true
    }
    private fun setupInitialData() {
        binding.myRecyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)
        viewModel.dispatchViewAction(SelectNewFavoriteStockAction.Init)
    }
    /* override fun onCreate(savedInstanceState: Bundle?)=SelectNewFavoriteStockActivityBinding {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.fragment_select_new_favorite_stock)
         search_view.setOnQueryTextListener(this)
         setupInitialData()
     }




     override fun onQueryTextSubmit(query: String?): Boolean {
        Log.i("AAAAA", "onQueryTextSubmit --> $query")
         return false
     }

     override fun onQueryTextChange(newText: String?): Boolean {
         Log.i("AAAA", "onQueryTextChange --> $newText")
         viewModel.dispatchViewAction(SelectNewFavoriteStockAction.SearchStock(newText))
         return true

     }*/
}