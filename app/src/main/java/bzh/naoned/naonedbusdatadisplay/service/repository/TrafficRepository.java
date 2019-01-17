package bzh.naoned.naonedbusdatadisplay.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrafficRepository {
    private static TrafficRepository trafficRepository;
    Realm realm;
    private NaonedService apiService;

    public TrafficRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NaonedService.HTTPS_API_NAONED_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(NaonedService.class);
        this.realm = Realm.getDefaultInstance();
    }

    public synchronized static TrafficRepository getInstance() {
        if (trafficRepository == null) {
            trafficRepository = new TrafficRepository();
        }
        return trafficRepository;
    }

    public LiveData<List<TrafficData>> getTrafficList() {
        final MutableLiveData<List<TrafficData>> data = new MutableLiveData<>();
        apiService.getTraffic().enqueue(new Callback<List<TrafficData>>() {
            @Override
            public void onResponse(Call<List<TrafficData>> call, Response<List<TrafficData>> response) {
                List<TrafficData> trafficDataList = response.body();
                data.setValue(trafficDataList);
                realm.beginTransaction();
                realm.delete(TrafficData.class);
                realm.copyToRealm(trafficDataList);
                realm.commitTransaction();
            }

            @Override
            public void onFailure(Call<List<TrafficData>> call, Throwable t) {
                List<TrafficData> trafficDataList = realm.where(TrafficData.class).findAll();
                if (!trafficDataList.isEmpty()) {
                    data.setValue(trafficDataList);
                    return;
                }
                data.setValue(null);
            }
        });

        return data;
    }

    public TrafficData getTraffic(String trafficId) {
        return realm.where(TrafficData.class).equalTo("mId", trafficId).findFirst();
    }
}
