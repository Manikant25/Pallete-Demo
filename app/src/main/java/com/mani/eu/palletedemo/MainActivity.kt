package com.mani.eu.palletedemo

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Step 4: Instance of ParentLayout and ImageView.
        // START
        val clMain = findViewById<ConstraintLayout>(R.id.cl_main)
        val imageMain = findViewById<ImageView>(R.id.iv_main)
        // END

        // TODO Step 5: Load the image from a random link from google.
        // START
        Glide.with(this)
                // To check the error you can change the link by adding some char
                .load("https://wi-images.condecdn.net/image/vvlaamRxxay/crop/2040/f/wired-uk-android-tips-1.jpg")
                // TODO Step 6: Add the listener
                // START
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            @Nullable e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        // log exception
                        Log.e("TAG", "Error loading image", e)
                        return false // important to return false so the error placeholder can be placed
                    }

                    override fun onResourceReady(
                            resource: Drawable,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {

                        // TODO Step 7: Once the resource is ready generate the Palette from the bitmap of image.
                        // START
                        Palette.from(resource.toBitmap())
                                .generate { palette ->

                                    // TODO Step 8: Get the vibrantSwatch color from the image and set it to the parent layout background.
                                    // START
                                    palette?.let {
                                        val intColor = it.vibrantSwatch?.rgb ?: 0
                                        clMain.setBackgroundColor(intColor)
                                    }
                                    // END
                                }
                        // END
                        return false
                    }
                })
                // END
                .into(imageMain)
        // END
    }
}