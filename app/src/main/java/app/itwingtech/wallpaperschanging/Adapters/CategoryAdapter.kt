package app.itwingtech.wallpaperschanging.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.itwingtech.wallpaperschanging.ModelClass.CategoryModel
import app.itwingtech.wallpaperschanging.R
import com.bumptech.glide.Glide

class CategoryAdapter(
    private val categories: List<CategoryModel>,
    private val onItemClick: (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        val context = holder.itemView.context
        Glide.with(context).load(category.image).thumbnail(0.1f).into(holder.imageView)
        holder.textView.text = category.name
        holder.itemView.setOnClickListener { onItemClick(category) }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val imageView: ImageView = itemView.findViewById(R.id.category_image)
        val textView: TextView = itemView.findViewById(R.id.category_name)

    }
}
