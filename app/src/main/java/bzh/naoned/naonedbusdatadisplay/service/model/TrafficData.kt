package bzh.naoned.naonedbusdatadisplay.service.model

import com.google.gson.annotations.SerializedName

import java.text.DateFormat
import java.util.Date
import java.util.Locale

import io.realm.RealmList
import io.realm.RealmObject

open class TrafficData : RealmObject() {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("dateStart")
    private var mDate: Date? = null
    @SerializedName("sourceCode")
    var sourceCode: String? = null
    @SerializedName("sourceLabel")
    var sourceLabel: String? = null
    @SerializedName("sourcePicture")
    var sourcePicture: String? = null
    @SerializedName("targets")
    var targets: RealmList<Target>? = null

    var date: String? = null
        get() = java.text.SimpleDateFormat("dd/MM/yyyy à HH:mm", Locale.getDefault()).format(mDate)
}
