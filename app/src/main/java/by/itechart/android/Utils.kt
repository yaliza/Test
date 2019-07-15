package by.itechart.android

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty


fun <T> didSet(initialValue: T, didSet: () -> Unit) = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = didSet()
}

fun ImageView.load(@DrawableRes imgId: Int) = Glide.with(this).load(imgId).into(this)