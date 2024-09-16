package app.itwingtech.wallpaperschanging.Fragments

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import app.itwingtech.wallpaperschanging.Adapters.GifWallpaperService
import app.itwingtech.wallpaperschanging.Adapters.LiveWallpaperAdapter
import app.itwingtech.wallpaperschanging.ModelClass.DataObject.list
import app.itwingtech.wallpaperschanging.ModelClass.GlobClass
import app.itwingtech.wallpaperschanging.databinding.FragmentWallpaperBinding

class wallpaperFragment : Fragment() {

    val binding: FragmentWallpaperBinding by lazy {
        FragmentWallpaperBinding.inflate(layoutInflater)
    }
    private lateinit var livewallpaperAdapter: LiveWallpaperAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerInit()
        return binding.root
    }

    fun recyclerInit() {

        binding.livewallpaperRec.layoutManager = GridLayoutManager(context, 2)

        livewallpaperAdapter = LiveWallpaperAdapter(list) { data ->
            val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER).apply {
                putExtra(
                    WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    ComponentName(requireContext(), GifWallpaperService::class.java)
                )
            }
            GlobClass.data = data.LiveWallpaperImage
            startActivity(intent)

        }
        binding.livewallpaperRec.adapter = livewallpaperAdapter


    }
}