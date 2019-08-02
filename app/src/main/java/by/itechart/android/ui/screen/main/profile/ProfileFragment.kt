package by.itechart.android.ui.screen.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.loadCircle
import by.itechart.android.ext.showMessage
import by.itechart.android.ui.entity.InvitationUiModel
import by.itechart.android.ui.screen.main.profile.recycler.CertificatesAdapter
import by.itechart.android.ui.screen.main.profile.recycler.HorizontalMarginItemDecorator
import by.itechart.android.ui.screen.main.profile.recycler.ScoresAdapter
import by.itechart.android.ui.screen.main.profile.recycler.SociableAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModel<ProfileViewModel>()
    private lateinit var certificatesAdapter: CertificatesAdapter
    private lateinit var scoresAdapter: ScoresAdapter
    private lateinit var sociableAdapter: SociableAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCertificatesRecycler()
        setupScoreRecyclerView()
        setupUsersRecycler()

        viewModel.profile.observe(viewLifecycleOwner, Observer { updateUserInfo(it) })
        viewModel.certificates.observe(viewLifecycleOwner, Observer { certificatesAdapter.items = it })
        viewModel.scores.observe(viewLifecycleOwner, Observer { scoresAdapter.items = it })
        viewModel.sociableLiveData.observe(viewLifecycleOwner, Observer { sociableAdapter.items = it })
        viewModel.followers.observe(
            viewLifecycleOwner,
            Observer { sociableTabLayout.getTabAt(1)?.text = getString(R.string.tab_followers, it.size) })
        viewModel.following.observe(
            viewLifecycleOwner,
            Observer { sociableTabLayout.getTabAt(0)?.text = getString(R.string.tab_following, it.size) })

        seeAllButton.setOnClickListener { showMessage("See all") }

        sociableTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when (p0?.position) {
                    0 -> viewModel.observeFollowing()
                    1 -> viewModel.observeFollowers()
                    2 -> viewModel.observeInvitations()
                }
            }
        })
    }

    private fun setupCertificatesRecycler() {
        certificatesAdapter = CertificatesAdapter()
        certificatesRecyclerView.apply {
            adapter = certificatesAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            addItemDecoration(
                HorizontalMarginItemDecorator(resources.getDimension(R.dimen.M), resources.getDimension(R.dimen.S))
            )
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }

    private fun setupScoreRecyclerView() {
        scoresAdapter = ScoresAdapter()
        scoresRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = scoresAdapter
        }
    }

    private fun setupUsersRecycler() {
        sociableAdapter = SociableAdapter()
        sociableAdapter.apply {
            userClickListener = { showMessage(it.name) }
            invitationClickListener = { invitation: InvitationUiModel, accepted: Boolean ->
                showMessage("${invitation.name} invitation was accepted -  $accepted")
            }
        }
        usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = sociableAdapter
        }
    }

    private fun updateUserInfo(user: User) = with(user) {
        userNameTextView.text = name
        userAvatarImageView.loadCircle(photoUrl)
    }
}