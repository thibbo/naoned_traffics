package bzh.naoned.naonedbusdatadisplay

import android.app.Application

import io.realm.Realm

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
