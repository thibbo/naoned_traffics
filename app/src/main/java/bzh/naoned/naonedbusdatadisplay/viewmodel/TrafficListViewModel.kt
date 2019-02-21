package bzh.naoned.naonedbusdatadisplay.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData
import bzh.naoned.naonedbusdatadisplay.service.repository.TrafficRepository

class TrafficListViewModel(application: Application) : AndroidViewModel(application) {
    private val trafficRepo : TrafficRepository = TrafficRepository.getInstance()
    var trafficListObservable: LiveData<List<TrafficData>>? = null

    fun init() {
        if (this.trafficListObservable != null) return
        trafficListObservable = trafficRepo.getTrafficList()
    }
}
