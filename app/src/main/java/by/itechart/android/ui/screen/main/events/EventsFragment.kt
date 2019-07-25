package by.itechart.android.ui.screen.main.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.ext.navigate
import kotlinx.android.synthetic.main.fragment_events.*


class EventsFragment : Fragment(R.layout.fragment_events) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSwipable.setOnClickListener { navigate(R.id.action_bottomNavFragment_to_swipableFragment) }
    }
}