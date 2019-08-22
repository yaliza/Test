package by.itechart.android.ui.screen.main.events

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
import by.itechart.android.ui.entity.DialogType
import by.itechart.android.ui.entity.DialogUIModel
import by.itechart.android.ui.screen.main.community.CommunityFragmentDirections
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class EventsFragment : Fragment(R.layout.fragment_events) {

    private val viewModel by viewModel<EventsViewModel>()
    private var dialog: Dialog? = null
    private val googleSignInClient by inject<GoogleSignInClient>()
    private val facebookLoginManager by inject<LoginManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutButton.setOnClickListener {
            viewModel.setupDialog(DialogUIModel(DialogType.ACCEPT, "Do you really want to logout?"))
        }
        infoDialogButton.setOnClickListener {
            viewModel.setupDialog(DialogUIModel(DialogType.INFO, "Info dialog message", "You have opened info dialog"))
        }
        bottomInfoDialogButton.setOnClickListener {
            viewModel.setupDialog(
                DialogUIModel(
                    dialogType = DialogType.BOTTOM,
                    message = "You have opened bottom info dialog",
                    icon = R.drawable.ic_award
                )
            )
        }
        viewModel.dialog.observe(viewLifecycleOwner, Observer { it?.let { showDialog(it) } })

        goToModularButton.setOnClickListener { navigate(R.id.action_eventsFragment_to_modalFragment) }
    }

    private fun showDialog(dialogUIModel: DialogUIModel) = context?.let {
        dialog?.dismiss()
        var acceptListener = { dismissDialog() }
        var declineListener: (() -> Unit)? = null
        var anim: Dialog.Animation? = null

        when (dialogUIModel.dialogType) {
            DialogType.ACCEPT -> {
                acceptListener = { dismissDialog(); logout() }
                declineListener = { dismissDialog() }
            }
            DialogType.INFO -> anim = Dialog.Animation.RIGHT
            DialogType.BOTTOM -> anim = Dialog.Animation.BOTTOM
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
        navigate(CommunityFragmentDirections.actionCommunityFragmentToLoginFragment())
    }
}