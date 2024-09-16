package app.itwingtech.wallpaperschanging.Activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.WallpaperManager
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import app.itwingtech.wallpaperschanging.R
import app.itwingtech.wallpaperschanging.databinding.ActivityPreviewBinding
import com.bumptech.glide.Glide

class PreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageResId = intent.getIntExtra("image", 0)
        if (imageResId != 0) {
            Glide.with(this)
                .load(imageResId)
                .into(binding.previewImg)
        }
        binding.previewImg.setImageResource(imageResId)

        binding.previewBtn.setOnClickListener {
            binding.previewBtn.isVisible = false
            binding.setwallpaperBtn.isVisible = false
        }

        binding.setwallpaperBtn.setOnClickListener {
            showWallpaperDialog(imageResId)
        }
    }

    @SuppressLint("ResourceType", "ObsoleteSdkInt")

    private fun showWallpaperDialog(imageResId: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setGravity(Gravity.BOTTOM)

        val lockScreenText: TextView = dialog.findViewById(R.id.lockscreen_text)
        val homeScreenText: TextView = dialog.findViewById(R.id.homescereentext)
        val bothText: TextView = dialog.findViewById(R.id.both_text)

        val wallpaperManager = WallpaperManager.getInstance(this)

        lockScreenText.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setResource(imageResId, WallpaperManager.FLAG_LOCK)
            } else {
            }
            dialog.dismiss()
        }
        homeScreenText.setOnClickListener {
            wallpaperManager.setResource(imageResId, WallpaperManager.FLAG_SYSTEM)
            dialog.dismiss()
        }
        bothText.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                wallpaperManager.setResource(imageResId, WallpaperManager.FLAG_SYSTEM)
                wallpaperManager.setResource(imageResId, WallpaperManager.FLAG_LOCK)
            } else {
                wallpaperManager.setResource(imageResId)
            }
            dialog.dismiss()
        }

        dialog.show()
    }

}