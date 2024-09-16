package app.itwingtech.wallpaperschanging.Adapters

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.LinearSmoothScroller.SNAP_TO_START

class SmoothScroller(context: Context) : LinearSmoothScroller(context) {

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return 150f / displayMetrics.densityDpi
    }
}




