package tk.zwander.rootactivitylauncher.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlin.math.floor

private fun Context.linearLayoutManager(): LinearLayoutManager{
    return LinearLayoutManager(this)
}

private fun Context.gridLayoutManager(spans: Int): StaggeredGridLayoutManager{
    return StaggeredGridLayoutManager(spans, StaggeredGridLayoutManager.VERTICAL)
}

fun Context.getAppropriateLayoutManager(widthDp: Int): RecyclerView.LayoutManager {
    return when {
        widthDp >= 800 -> gridLayoutManager(floor(widthDp / 400f).toInt())
        else -> linearLayoutManager()
    }
}

fun RecyclerView.LayoutManager.findFirstVisibleItemPosition(): Int {
    if (this is LinearLayoutManager) {
        return findFirstVisibleItemPosition()
    } else if (this is StaggeredGridLayoutManager) {
        return findFirstVisibleItemPositions(null)[0]
    }

    return -1
}

fun RecyclerView.LayoutManager.findLastVisibleItemPosition(): Int {
    if (this is LinearLayoutManager) {
        return findLastVisibleItemPosition()
    } else if (this is StaggeredGridLayoutManager) {
        return findLastVisibleItemPositions(null).last()
    }

    return -1
}