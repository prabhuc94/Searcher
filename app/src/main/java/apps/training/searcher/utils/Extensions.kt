package apps.training.searcher.utils

import android.view.View
import android.widget.ImageView
import apps.training.searcher.R
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

object Extensions {
    // Try to make ternary operator in Kotlin
    fun <T> T?.or(default: T): T = this ?: default

    fun <T> T?.or(compute: () -> T): T = this ?: compute()

    fun View.show(){
        this.visibility = View.VISIBLE
    }

    fun View.hide(){
        this.visibility = View.GONE
    }


    fun ImageView.load(imagePath : Any?){
        GlideApp.with(this)
            .load(imagePath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
    }
}