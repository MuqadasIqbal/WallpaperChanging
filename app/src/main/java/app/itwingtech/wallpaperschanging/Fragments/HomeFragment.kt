package app.itwingtech.wallpaperschanging.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.itwingtech.wallpaperschanging.Adapters.FragmentStateAdapter
import app.itwingtech.wallpaperschanging.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: FragmentStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FragmentStateAdapter(childFragmentManager)

        adapter.addFragment(HomeRecyclerFragment(), "Home")
        adapter.addFragment(wallpaperFragment(), "Live Wallpaper")


        binding.viewpager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }


}