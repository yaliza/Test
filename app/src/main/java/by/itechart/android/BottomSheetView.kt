package by.itechart.android

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.view_bottom_sheet.view.*

class BottomSheetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var cardViewRadius: Float

    init {
        ConstraintLayout.inflate(context, R.layout.view_bottom_sheet, this)
        cardView.setOnTouchListener { _, _ -> true }
        cardViewRadius = cardView.radius
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupBottomSheetBehaviour()
    }

    fun setContentLayout(view: View) {
        bottomSheetContainerFrameLayout.apply {
            removeAllViews()
            addView(view)
        }
    }

    private fun setupBottomSheetBehaviour() {
        val bottomSheetBehavior = BottomSheetBehavior.from(this)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                peek.alpha = (1.0f - (slideOffset) * 2)
                icExpand.alpha = -(1.0f - (slideOffset) * 4)
                cardView.radius = cardViewRadius * slideOffset
            }
        })
    }
}