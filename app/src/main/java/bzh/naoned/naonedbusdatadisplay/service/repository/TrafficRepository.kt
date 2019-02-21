package bzh.naoned.naonedbusdatadisplay.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrafficRepository private constructor(){
    private val realm: Realm
    private val apiService: NaonedService

    fun getTrafficList() : LiveData<List<TrafficData>> {
            val data = MutableLiveData<List<TrafficData>>()
            apiService.traffic().enqueue(object : Callback<List<TrafficData>> {
                override fun onResponse(call: Call<List<TrafficData>>, response: Response<List<TrafficData>>) {
                    var trafficDataList = response.body()
                    data.value = trafficDataList
                    realm.beginTransaction()
                    realm.delete(TrafficData::class.java)
                    realm.copyToRealm(trafficDataList)
                    realm.commitTransaction()
                }

                override fun onFailure(call: Call<List<TrafficData>>, t: Throwable) {
                    val trafficDataList = realm.where(TrafficData::class.java).findAll()
                    if (!trafficDataList.isEmpty()) {
                        data.value = trafficDataList
                        return
                    }
                    data.value = null
                }
            })

            return data
        }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(NaonedService.HTTPS_API_NAONED_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        this.apiService = retrofit.create(NaonedService::class.java)
        this.realm = Realm.getDefaultInstance()
    }

    fun getTraffic(trafficId: String): TrafficData? {
        return realm.where(TrafficData::class.java).equalTo("id", trafficId).findFirst()
    }

    companion object {
        private val mInstance: TrafficRepository = TrafficRepository()
        @Synchronized
        fun getInstance(): TrafficRepository {
            return mInstance
        }
    }
}
