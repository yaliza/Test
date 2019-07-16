package by.itechart.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.itechart.android.data.entities.User
import by.itechart.android.data.repositories.FacebookRepositoryImpl
import by.itechart.android.data.repositories.FacebookRepositoryRetroImpl
import com.facebook.login.LoginManager
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_school.*

class SchoolFragment : Fragment(R.layout.fragment_school) {

    private val compositeDisposable = CompositeDisposable()

    private val userObserver = object : SingleObserver<User> {
        override fun onSuccess(t: User) {
            schoolLabel.text = "${t.name} ${t.email}"
        }

        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        override fun onError(e: Throwable) = Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btLogout.setOnClickListener { LoginManager.getInstance().logOut() }
        btSwipable.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_bottomNavFragment_to_swipableFragment)
        }

        FacebookRepositoryRetroImpl().apply {
            getCurrentUserInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(userObserver)
        }
    }
}