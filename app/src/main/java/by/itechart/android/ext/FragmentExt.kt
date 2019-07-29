package by.itechart.android.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import by.itechart.android.R


fun Fragment.navigate(actionID: Int, navHostID: Int = R.id.navHostFragment) {
    activity?.let { act: FragmentActivity ->
        Navigation.findNavController(act, navHostID).navigate(actionID)
    }
}

fun Fragment.navigate(direction: NavDirections, navHostID: Int = R.id.navHostFragment) {
    activity?.let { act: FragmentActivity ->
        Navigation.findNavController(act, navHostID).navigate(direction)
    }
}

fun Fragment.showMessage(msg: String) = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()