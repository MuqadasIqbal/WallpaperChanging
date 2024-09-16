package app.itwingtech.wallpaperschanging.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.itwingtech.wallpaperschanging.Adapters.CategoryAdapter
import app.itwingtech.wallpaperschanging.Adapters.SmoothScroller
import app.itwingtech.wallpaperschanging.Adapters.TopwallpaperAdapter
import app.itwingtech.wallpaperschanging.ModelClass.CategoryModel
import app.itwingtech.wallpaperschanging.ModelClass.topWallpaperModel
import app.itwingtech.wallpaperschanging.R
import app.itwingtech.wallpaperschanging.databinding.FragmentHomeRecyclerBinding

class HomeRecyclerFragment : Fragment() {
    private lateinit var topWallpaperAdapter: TopwallpaperAdapter
    val binding: FragmentHomeRecyclerBinding by lazy {
        FragmentHomeRecyclerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerview.layoutManager = linearLayoutManager
        binding.categoryRecyclerview.adapter = CategoryAdapter(getData()) { selectedCategory ->
            filterWallpapers(selectedCategory.name)
        }
        val smoothScroller = SmoothScroller(requireContext())
        smoothScroller.targetPosition = 1
        linearLayoutManager.startSmoothScroll(smoothScroller)

        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.topWallpaperRec.layoutManager = gridLayoutManager
        topWallpaperAdapter = TopwallpaperAdapter(getDatat2(), requireContext())
        binding.topWallpaperRec.adapter = topWallpaperAdapter


        return binding.root
    }

    private fun getData(): List<CategoryModel> {
        return listOf(
            CategoryModel(R.drawable.brid_img1, "Birds"),
            CategoryModel(R.drawable.cat, "Animal"),
            CategoryModel(R.drawable.nature_img6, "Nature")
        )
    }

    private fun getDatat2(): List<topWallpaperModel> {
        return listOf(
            topWallpaperModel(R.drawable.sparrow, "Birds"),
            topWallpaperModel(R.drawable.bird_img6, "Birds"),
            topWallpaperModel(R.drawable.brid_img1, "Birds"),
            topWallpaperModel(R.drawable.parrot2, "Birds"),
            topWallpaperModel(R.drawable.nature1, "Nature"),
            topWallpaperModel(R.drawable.cat2, "Animal"),
            topWallpaperModel(R.drawable.bird_img, "Birds"),
            topWallpaperModel(R.drawable.nature2, "Nature"),
            topWallpaperModel(R.drawable.nature_img6, "Nature"),
            topWallpaperModel(R.drawable.cat, "Animal")
        )
    }

    //Used to filter wallpaper base of category
    private fun filterWallpapers(categoryName: String) {
        val filteredList = getDatat2().filter { it.topwallpaperName == categoryName }
        topWallpaperAdapter.updateData(filteredList)
    }
}
