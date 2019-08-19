package by.itechart.android.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import by.itechart.android.R
import kotlinx.android.synthetic.main.view_bottom_nav.view.*

class BottomNavView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val menuItemMaxWidth: Int
    private var marginSet = false
    var bottomNavListener: ((Int) -> Unit)? = null
    val menuIds = mutableListOf<Int>()

    var selected: Int = 0
        set(value) {
            if (navBottomBar.selectedItemId != value) {
                navBottomBar.selectedItemId = value
            }
            field = value
        }

    init {
        inflate(context, R.layout.view_bottom_nav, this)
        initAttributes(attrs)
        menuItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width)
        setupBottomNavBar()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!marginSet) {
            marginSet = true
            navDotsIndicator.requestLayout()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val dotsSpace: Int
        val padding: Int

        if (menuItemMaxWidth * menuIds.size > w) {
            dotsSpace = w / (menuIds.size) - 2 * navDotsIndicator.radius
            padding = dotsSpace / 2
        } else {
            dotsSpace = (menuItemMaxWidth - navDotsIndicator.radius * 2)
            padding = dotsSpace / 2 + ((w - menuIds.size * menuItemMaxWidth) / 2)
        }

        val params = navDotsIndicator.layoutParams as LayoutParams
        params.setMargins(padding, params.topMargin, padding, params.bottomMargin)
        navDotsIndicator.apply {
            layoutParams = params
            setPadding(dotsSpace.toFloat())
        }
    }

    private fun changeDotSelectedIndex(itemId: Int): Boolean {
        val index = menuIds.indexOf(itemId)
        return if (index != -1) {
            post { navDotsIndicator.selection = index }
            true
        } else false
    }

    private fun initAttributes(attrs: AttributeSet?) =
        context.theme.obtainStyledAttributes(attrs, R.styleable.BottomNavView, 0, 0).apply {
            if (hasValue(R.styleable.BottomNavView_bnv_menu)) {
                navBottomBar.inflateMenu(getResourceId(R.styleable.BottomNavView_bnv_menu, 0))
            }
            recycle()
        }

    private fun setupBottomNavBar() {
        navBottomBar.apply {
            menu.forEach { menuIds.add(it.itemId) }
            setOnNavigationItemSelectedListener {
                bottomNavListener?.invoke(it.itemId)
                changeDotSelectedIndex(it.itemId)
            }
        }
        navDotsIndicator.count = menuIds.size
    }
}