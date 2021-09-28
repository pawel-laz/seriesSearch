package pl.lazicki.seriessearch.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun View.setVisible(show: Boolean) {
    visibility = if (show) View.VISIBLE
    else View.GONE
}

fun ImageView.loadImage(url: String) =
    Glide.with(this.context)
        .load(url)
        .into(this)