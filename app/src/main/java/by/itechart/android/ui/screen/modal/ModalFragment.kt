package by.itechart.android.ui.screen.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.ModalCardUIModel
import kotlinx.android.synthetic.main.fragment_modal.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ModalFragment : Fragment(R.layout.fragment_modal) {

    private val viewModel by viewModel<ModalViewModel>()
    private val modalCardsAdapter = ModalCardsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipableViewPager.apply {
            adapter = modalCardsAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) =
                    swipableBottomSheetView.setContentLayout(
                        LayoutInflater.from(context).inflate(modalCardsAdapter.items[position].layout, null)
                    )
            })
        }

        viewModel.modalCards.observe(viewLifecycleOwner, object : ResourceObserver<List<ModalCardUIModel>>() {
            override fun onLoading() = progressBar.show()
            override fun onSuccess(data: List<ModalCardUIModel>?) {
                progressBar.hide()
                data?.let { modalCardsAdapter.items = data }
            }
            override fun onError(message: String) {
                progressBar.hide()
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        })
    }

}