package app.itwingtech.wallpaperschanging.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.itwingtech.wallpaperschanging.ModelClass.GlobClass.data
import app.itwingtech.wallpaperschanging.ModelClass.LiveWallPaperModel
import app.itwingtech.wallpaperschanging.R
import com.bumptech.glide.Glide

class LiveWallpaperAdapter(
    private val videoList: List<LiveWallPaperModel>,
    private val onItemClick: (LiveWallPaperModel) -> Unit
) : RecyclerView.Adapter<LiveWallpaperAdapter.VideoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.livewallpaperlayout_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val data = videoList[position]
        val context = holder.itemView.context
        Glide.with(context).load(data.LiveWallpaperImage).thumbnail(0.1f).into(holder.imageView)
        holder.itemView.setOnClickListener {
            onItemClick(data)
        }
    }

    override fun getItemCount(): Int = videoList.size

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.video_view)
    }
}