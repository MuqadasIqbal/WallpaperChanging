package app.itwingtech.wallpaperschanging.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.itwingtech.wallpaperschanging.Activities.PreviewActivity
import app.itwingtech.wallpaperschanging.ModelClass.GlobClass.data
import app.itwingtech.wallpaperschanging.ModelClass.topWallpaperModel
import app.itwingtech.wallpaperschanging.R
import com.bumptech.glide.Glide


class TopwallpaperAdapter(
    private var wallpapers: List<topWallpaperModel>,
    private val context: Context
) : RecyclerView.Adapter<TopwallpaperAdapter.TopWallpaperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopWallpaperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.topwalllpaper_layout, parent, false)
        return TopWallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopWallpaperViewHolder, position: Int) {
        val wallpaper = wallpapers[position]
        val context = holder.itemView.context
        Glide.with(context).load(wallpaper.topwallpaperImage).thumbnail(0.1f).into(holder.imageView)

        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val isFavorite = sharedPreferences.getBoolean(wallpaper.topwallpaperImage.toString(), false)
        holder.favouriteImg.setImageResource(
            if (isFavorite) R.drawable.favorite_red else R.drawable.baseline_favorite_24
        )

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PreviewActivity::class.java)
            intent.putExtra("image", wallpaper.topwallpaperImage)
            context.startActivity(intent)
        }

        holder.favouriteImg.setOnClickListener {
            val editor = sharedPreferences.edit()
            if (isFavorite) {
                holder.favouriteImg.setImageResource(R.drawable.baseline_favorite_24)
                editor.remove(wallpaper.topwallpaperImage.toString())
            } else {
                holder.favouriteImg.setImageResource(R.drawable.favorite_red)
                editor.putBoolean(wallpaper.topwallpaperImage.toString(), true)
            }
            editor.apply()

        }
    }

    override fun getItemCount(): Int = wallpapers.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newWallpapers: List<topWallpaperModel>) {
        wallpapers = newWallpapers
        notifyDataSetChanged()
    }

    class TopWallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.topwallpaper_image)
        val favouriteImg: ImageView = itemView.findViewById(R.id.fsvourite_img)
    }
}


