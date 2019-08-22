package by.itechart.android.ui.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class HorizontalOverlappingItemDecorator(
    private val space: Float
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != 0)
            outRect.left = space.toInt()
    }

}