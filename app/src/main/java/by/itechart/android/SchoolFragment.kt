package by.itechart.android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_school.*

class SchoolFragment : Fragment(R.layout.fragment_school) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btLogout.setOnClickListener { LoginManager.getInstance().logOut() }
    }
}