package by.itechart.android.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.menuOpenImageView
import kotlinx.android.synthetic.main.view_navigation_drawer.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuOpenImageView.setOnClickListener { mainDrawerLayout.openDrawer(sideMenuNavigation) }
        menuCloseImageView.setOnClickListener { mainDrawerLayout.closeDrawer(sideMenuNavigation) }

        val navController = findNavController(R.id.primaryNavHostFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (bottomNavView.menuIds.contains(destination.id)) {
                bottomNavView.show()
                menuOpenImageView.show()
                bottomNavView.selected = destination.id
                mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                bottomNavView.hide()
                menuOpenImageView.hide()
                mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        bottomNavView.bottomNavListener = { itemId: Int ->
            if (navController.currentDestination?.id != itemId) {
                with(navController) {
                    popBackStack()
                    navigate(itemId)
                }
            }
        }
    }

}
