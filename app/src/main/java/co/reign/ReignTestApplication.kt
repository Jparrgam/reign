package co.reign

import androidx.multidex.MultiDexApplication
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class ReignTestApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}