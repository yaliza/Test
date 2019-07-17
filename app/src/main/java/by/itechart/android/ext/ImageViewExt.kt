package by.itechart.android.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide


fun ImageView.load(@DrawableRes imgId: Int) = Glide.with(this).load(imgId).into(this)