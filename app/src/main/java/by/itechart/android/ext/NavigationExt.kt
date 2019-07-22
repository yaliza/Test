package by.itechart.android.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import by.itechart.android.R


fun Fragment.navigate(actionID: Int, navHostID: Int = R.id.navHostFragment) {
    activity?.let { act: FragmentActivity ->
        Navigation.findNavController(act, navHostID).navigate(actionID)
    }
}