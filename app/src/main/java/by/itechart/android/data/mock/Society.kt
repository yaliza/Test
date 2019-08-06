package by.itechart.android.data.mock

import by.itechart.android.R
import by.itechart.android.ui.entity.InvitationUiModel
import by.itechart.android.ui.entity.UserUIModel


object Society {

    val mockFollowers = listOf(
        UserUIModel("Roman Elizarov", R.drawable.avatar_4_raster),
        UserUIModel("Jake Wharton", R.drawable.avatar_5_raster),
        UserUIModel("Florina Muntenescu", R.drawable.avatar_9_raster),
        UserUIModel("Elon Musk", R.drawable.avatar_6_raster)
    )

    val mockFollowing = listOf(
        UserUIModel("Carlos Pereira", R.drawable.avatar_1_raster),
        UserUIModel("Tom Grisewood", R.drawable.avatar_2_raster),
        UserUIModel("Sarah Elizabeth", R.drawable.avatar_3_raster)
    )

    val mockInvitations = listOf(
        InvitationUiModel("Mickey Mouse", R.drawable.avatar_7_raster),
        InvitationUiModel("Donald Duck", R.drawable.avatar_8_raster)

    )
}