package by.itechart.android.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import by.itechart.android.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


fun ImageView.load(@DrawableRes imgId: Int) = Glide.with(this).load(imgId).into(this)

fun ImageView.load(url: String?) = Glide.with(this).load(url).into(this)

fun ImageView.loadRounded(url: String?) =
    Glide.with(this)
        .load(url)
        .transform(RoundedCorners(25))
        .into(this)

fun ImageView.loadCircle(url: String?) =
    Glide.with(this)
        .load(url)
        .error(R.drawable.avatar_circle)
        .apply(RequestOptions.circleCropTransform())
        .into(this)