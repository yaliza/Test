package by.itechart.android.ui.screen.main.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.itechart.android.R
import by.itechart.android.di.dataModule
import by.itechart.android.ext.navigate
import by.itechart.android.ext.showMessage
import by.itechart.android.ui.dialog.Dialog
import by.itechart.android.ui.dialog.dialog
import by.itechart.android.ui.entity.DialogType.*
import by.itechart.android.ui.entity.DialogUIModel
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_community.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class CommunityFragment : Fragment(R.layout.fragment_community) {

    private val googleSignInClient by inject<GoogleSignInClient>()
    private val facebookLoginManager by inject<LoginManager>()
    private val viewModel by viewModel<CommunityViewModel>()
    private var dialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutButton.setOnClickListener {
            viewModel.setupDialog(DialogUIModel(ACCEPT, "Do you really want to logout?"))
        }
        infoDialogButton.setOnClickListener {
            viewModel.setupDialog(DialogUIModel(INFO, "Info dialog message", "You have opened info dialog"))
        }
        bottomInfoDialogButton.setOnClickListener {
            viewModel.setupDialog(
                DialogUIModel(
                    dialogType = BOTTOM,
                    message = "You have opened bottom info dialog",
                    icon = R.drawable.ic_award
                )
            )
        }
        viewModel.dialog.observe(viewLifecycleOwner, Observer { it?.let { showDialog(it) } })

        goToModularButton.setOnClickListener { navigate(R.id.action_bottomNavFragment_to_swipableFragment) }
    }

    private fun showDialog(dialogUIModel: DialogUIModel) = context?.let {

            dialog?.dismiss()
            var acceptListener = { dismissDialog() }
            var declineListener: (() -> Unit)? = null
            var anim: Dialog.Animation? = null

            when (dialogUIModel.dialogType) {
                ACCEPT -> {
                    acceptListener = { dismissDialog(); logout() }
                    declineListener = { dismissDialog() }
                }
                INFO -> anim = Dialog.Animation.RIGHT
                BOTTOM -> anim = Dialog.Animation.BOTTOM
            }

            dialog = dialog {
                context = it
                uiModel = dialogUIModel
                acceptClickListener = acceptListener
                declineClickListener = declineListener
                dismissListener = { viewModel.setupDialog(null); dialog = null }
                animation = anim
            }

            dialog?.show()
        }

    private fun dismissDialog() {
        showMessage("dismissDialog")
        dialog?.dismiss()
    }

    private fun logout() {
        facebookLoginManager.logOut()
        googleSignInClient.signOut()
        unloadKoinModules(dataModule)
        loadKoinModules(dataModule)
        navigate(R.id.action_toLoginFragment)
    }

}