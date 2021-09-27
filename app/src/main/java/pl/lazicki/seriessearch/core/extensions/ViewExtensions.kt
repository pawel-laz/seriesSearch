package pl.lazicki.seriessearch.core.extensions

import android.view.View

fun View.setVisible(show: Boolean) = run {
    if (show) visibility = View.VISIBLE
    else View.GONE
}