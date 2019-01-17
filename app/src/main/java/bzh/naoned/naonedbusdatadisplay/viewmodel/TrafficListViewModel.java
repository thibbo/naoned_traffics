package bzh.naoned.naonedbusdatadisplay.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import bzh.naoned.naonedbusdatadisplay.service.repository.TrafficRepository;

public class TrafficListViewModel extends AndroidViewModel {
    LiveData<List<TrafficData>> trafficListObservable;

    public TrafficListViewModel(Application application) {
        super(application);
    }

    public void init() {
        if (this.trafficListObservable != null) return;
        trafficListObservable = TrafficRepository.getInstance().getTrafficList();
    }

    public LiveData<List<TrafficData>> getTrafficListObservable() {
        return this.trafficListObservable;
    }
}
