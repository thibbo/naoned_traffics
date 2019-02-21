package bzh.naoned.naonedbusdatadisplay.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.widget.ImageView

import com.squareup.picasso.Picasso

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData
import bzh.naoned.naonedbusdatadisplay.service.repository.TrafficRepository

class TrafficViewModel(application: Application) : AndroidViewModel(application) {
    private var trafficData: TrafficData? = null
    private val trafficRepo : TrafficRepository = TrafficRepository.getInstance()

    fun getTraffic(id: String): TrafficData? {
        if (trafficData == null)
            trafficData = trafficRepo.getTraffic(id)

        return trafficData
    }

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get()
                .load(imageUrl)
                .into(view)
    }
}
