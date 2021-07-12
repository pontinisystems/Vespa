package pontinisystems.vespa.presenter.products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_persistent.*
import pontinisystems.vespa.R
import pontinisystems.vespa.domain.entities.ProductUi

class BottomSheet(val productUi: ProductUi) : BottomSheetDialogFragment() {

    private var amountSelected=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bottom_sheet_persistent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateAmountTextView()
        buttonLess.setOnClickListener {
            amountSelected=amountSelected.dec()
            updateAmountTextView()

        }

        buttonPlus.setOnClickListener {
            amountSelected=amountSelected.plus(1)
            updateAmountTextView()

        }

        imgClose.setOnClickListener {
            this.dismiss()

        }

    }

    private fun updateAmountTextView() {
        tvAmount.text = amountSelected.toString()
        if(amountSelected<=0){
            btBuy.alpha =.5f;

        }else{
            btBuy.alpha = 1.0f
        }
    }


}