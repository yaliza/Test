package by.itechart.android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_swipable.*

class SwipableFragment : Fragment(R.layout.fragment_swipable) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        var height: Int? = null
        var radius: Float? = null

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (height == null) height = peek.height
                if (radius == null) radius = cardView.radius
                peek.alpha = (1.0f - (slideOffset) * 2)
                icExpand.alpha = -(1.0f - (slideOffset) * 4)
                cardView.radius = radius!! * slideOffset
            }
        })
    }
}