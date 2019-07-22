package by.itechart.android.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.load(@DrawableRes imgId: Int) = Glide.with(this).load(imgId).into(this)

fun ImageView.loadCircle(url: String) =
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)