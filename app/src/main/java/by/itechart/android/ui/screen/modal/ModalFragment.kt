package by.itechart.android.ui.screen.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import by.itechart.android.R
import by.itechart.android.ui.entity.ModalCardItem
import kotlinx.android.synthetic.main.fragment_swipable.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ModalFragment : Fragment(R.layout.fragment_swipable) {

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

        viewModel.modalCards.observe(this, Observer<List<ModalCardItem>> { modalCardsAdapter.items = it })

//        TODO: crash if list is empty
//        swipableBottomSheetView.setContentLayout(LayoutInflater.from(context).inflate(adapter.items[0].layout, null))

    }
}