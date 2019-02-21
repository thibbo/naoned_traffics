package bzh.naoned.naonedbusdatadisplay.service.repository

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData
import retrofit2.Call
import retrofit2.http.GET

interface NaonedService {

    @GET("traffics")
    fun traffic(): Call<List<TrafficData>>

    companion object {
        val HTTPS_API_NAONED_URL = "https://api.naonedbus.net/"
    }
}
