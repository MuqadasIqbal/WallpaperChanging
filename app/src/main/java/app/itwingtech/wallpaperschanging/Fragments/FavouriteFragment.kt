package app.itwingtech.wallpaperschanging.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.itwingtech.wallpaperschanging.Adapters.TopwallpaperAdapter
import app.itwingtech.wallpaperschanging.ModelClass.topWallpaperModel
import app.itwingtech.wallpaperschanging.R
import app.itwingtech.wallpaperschanging.databinding.FragmentFavouriteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteFragment : Fragment() {
    val binding: FragmentFavouriteBinding by lazy {
        FragmentFavouriteBinding.inflate(layoutInflater)
    }

    private lateinit var favoriteAdapter: TopwallpaperAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.favoriteRecyclerview.layoutManager = gridLayoutManager

        val favoriteWallpapers = getFavoriteWallpapers()
        favoriteAdapter = TopwallpaperAdapter(favoriteWallpapers, requireContext())
        binding.favoriteRecyclerview.adapter = favoriteAdapter

        return binding.root
    }

    private fun getFavoriteWallpapers(): List<topWallpaperModel> {
        val sharedPreferences =
            requireContext().getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val favoriteWallpapers = mutableListOf<topWallpaperModel>()

        val allFavorites = sharedPreferences.all
        allFavorites.forEach { (key, value) ->
            if (value as Boolean) {
                val resId = key.toInt()
                val name = "Your Category Name"
                favoriteWallpapers.add(topWallpaperModel(resId, name))
            }
        }
        return favoriteWallpapers
    }
}
