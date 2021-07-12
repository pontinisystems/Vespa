package pontinisystems.vespa.presenter.products.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import pontinisystems.vespa.R
import pontinisystems.vespa.databinding.FragmentProductsBinding
import pontinisystems.vespa.di.appModule
import pontinisystems.vespa.domain.entities.ProductUi
import pontinisystems.vespa.presenter.products.ui.adapter.ProductsAdapter
import pontinisystems.vespa.presenter.products.ui.viewaction.ItemProductAction
import pontinisystems.vespa.presenter.products.ui.viewstate.ProductViewState
import pontinisystems.vespa.presenter.products.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModel()
    private fun inject() = loadModules
    private lateinit var binding:FragmentProductsBinding

    private val adapter by lazy { ProductsAdapter(viewModel, viewModel) }

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
    )= FragmentProductsBinding.inflate(inflater,container,false).apply{

        setupView()
        observeChanges()
    }.root

    private fun FragmentProductsBinding.setupView() {

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
        val a = DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL)
        a.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!);
        binding.myRecyclerView.addItemDecoration(a)

        viewModel.dispatchViewAction(ItemProductAction.Fetch)
    }

    private fun observeChanges() {
        viewModel.viewState.action.observe(viewLifecycleOwner, Observer { action->
            when(action){
                is ProductViewState.Action.SetFavoriteStocksList->setListAdapter(action.list)
            }
        })
    }
    private fun setListAdapter(list: List<ProductUi>) {
        binding.myRecyclerView.adapter?.let {
            adapter.submitList(list)
            it.notifyDataSetChanged()
        }
        Log.i("SIZE ADAPTER","SIZE ADAPTER "+
                adapter.currentList.size)
    }

}