package by.itechart.android.ui.base

import android.view.View
import androidx.viewpager2.widget.ViewPager2


class CarouselPagerTransformer(private val space: Float) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.translationX = -(position * space)
    }
}