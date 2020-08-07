package apps.training.searcher.utils

import android.content.Context
import androidx.multidex.MultiDexApplication


class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}