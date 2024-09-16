package app.itwingtech.wallpaperschanging.Adapters

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import app.itwingtech.wallpaperschanging.ModelClass.GlobClass
import app.itwingtech.wallpaperschanging.R

class GifWallpaperService : WallpaperService() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreateEngine(): Engine {
        return VideoWallpaperEngine()
    }

    inner class VideoWallpaperEngine : Engine() {
        private var mediaPlayer: MediaPlayer? = null

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            mediaPlayer = MediaPlayer.create(this@GifWallpaperService, GlobClass.data)
            mediaPlayer?.start()
            mediaPlayer?.setSurface(holder.surface)
            mediaPlayer?.isLooping = true
            super.onSurfaceCreated(holder)
        }

    }
}



