package by.itechart.android.ui.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.itechart.android.R
import kotlinx.android.synthetic.main.fragment_bottom_nav.*
import kotlinx.android.synthetic.main.view_navigation_drawer.*


class BottomNavFragment : Fragment(R.layout.fragment_bottom_nav) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.secondaryNavHostFragment)
        bottomNavView.setupWithNavController(navController)

        menuOpenImageView.setOnClickListener { bottomNavFragmentDrawerLayout.openDrawer(sideMenuNavigation) }
        menuCloseImageView.setOnClickListener { bottomNavFragmentDrawerLayout.closeDrawer(sideMenuNavigation) }
    }
}