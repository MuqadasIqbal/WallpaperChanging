package app.itwingtech.wallpaperschanging.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.itwingtech.wallpaperschanging.Activities.PreviewActivity
import app.itwingtech.wallpaperschanging.ModelClass.SearchModel
import app.itwingtech.wallpaperschanging.R
import com.bumptech.glide.Glide


class SearchAdapter(private var searchImg: List<SearchModel>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_layout, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val image = searchImg[position]
        val context = holder.itemView.context
        Glide.with(context)
            .load(image.SearchImg).thumbnail(0.1f)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PreviewActivity::class.java)
            intent.putExtra("image", image.SearchImg)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = searchImg.size

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.search_image)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<SearchModel>) {
        searchImg = newData
        notifyDataSetChanged()
    }
}