package app.itwingtech.wallpaperschanging.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.itwingtech.wallpaperschanging.Adapters.SearchAdapter
import app.itwingtech.wallpaperschanging.Adapters.SmoothScroller
import app.itwingtech.wallpaperschanging.ModelClass.SearchModel
import app.itwingtech.wallpaperschanging.R
import com.mancj.materialsearchbar.MaterialSearchBar

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: MaterialSearchBar
    private lateinit var searchAdapter: SearchAdapter
    private var allData: List<SearchModel> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.search_recyclerview)
        searchBar = view.findViewById(R.id.searchBar)

        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager

        allData = getData()
        searchAdapter = SearchAdapter(allData)
        recyclerView.adapter = searchAdapter

        val smoothScroller = SmoothScroller(requireContext())
        smoothScroller.targetPosition = 1
        gridLayoutManager.startSmoothScroll(smoothScroller)

        setupSearchBar()

        return view
    }

    private fun setupSearchBar() {
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {
            }

            override fun onSearchConfirmed(query: CharSequence?) {
                filterData(query)
            }

            override fun onButtonClicked(buttonCode: Int) {
            }
        })

        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun filterData(query: CharSequence?) {
        val filteredList = if (query.isNullOrEmpty()) {
            allData
        } else {
            allData.filter {
                it.SearchText.contains(query, ignoreCase = true)
            }
        }
        searchAdapter.updateData(filteredList)
    }

    private fun getData(): List<SearchModel> {
        return listOf(
            SearchModel(R.drawable.bird_img6, "Bird"),
            SearchModel(R.drawable.sparrow, "Sparrow"),
            SearchModel(R.drawable.bird_img, "Parrot"),
            SearchModel(R.drawable.parrot2, "Parrot"),
            SearchModel(R.drawable.nature1, "Nature"),
            SearchModel(R.drawable.nature2, "Nature"),
            SearchModel(R.drawable.nature_img6, "Nature"),
            SearchModel(R.drawable.cat2, "Cat"),
            SearchModel(R.drawable.cat, "Cat"),
            SearchModel(R.drawable.brid_img1, "Bird")


        )
    }

}


