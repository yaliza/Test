package by.itechart.android.ui.screen.main.profile.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalMarginItemDecorator(private val outerMargin: Float, private val innerMargin: Float) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view)
        val leftInset = if (index == 0) outerMargin else innerMargin
        val rightInset = if (index == parent.adapter!!.itemCount - 1) outerMargin else innerMargin
        outRect.set(leftInset.toInt(), 0, rightInset.toInt(), 0)
    }
}