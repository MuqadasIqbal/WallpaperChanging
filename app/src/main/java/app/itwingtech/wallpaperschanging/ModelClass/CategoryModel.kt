package app.itwingtech.wallpaperschanging.ModelClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val image: Int,
    val name: String
) : Parcelable