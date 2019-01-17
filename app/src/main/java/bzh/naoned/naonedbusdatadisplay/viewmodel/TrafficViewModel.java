package bzh.naoned.naonedbusdatadisplay.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import bzh.naoned.naonedbusdatadisplay.service.repository.TrafficRepository;

public class TrafficViewModel extends AndroidViewModel {
    private TrafficData trafficData;

    public TrafficViewModel(@NonNull Application application) {
        super(application);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .into(view);
    }

    public TrafficData getTraffic(String id) {
        if (trafficData == null)
            trafficData = TrafficRepository.getInstance().getTraffic(id);

        return trafficData;
    }
}
