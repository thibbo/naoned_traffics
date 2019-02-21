package bzh.naoned.naonedbusdatadisplay.service.model

import android.graphics.Color

import com.google.gson.annotations.SerializedName

import io.realm.RealmObject

open class Target : RealmObject() {
    @SerializedName("routeCode")
    var routeCode: String? = null
    @SerializedName("routeLabel")
    var routeLabel: String? = null
    @SerializedName("routeColor")
    private var mRouteColor: String? = null

    fun getRouteColor(): Int? {
        return Color.parseColor(mRouteColor)
    }
}
