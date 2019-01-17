package bzh.naoned.naonedbusdatadisplay.service.repository;

import java.util.List;

import bzh.naoned.naonedbusdatadisplay.service.model.TrafficData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NaonedService {
    String HTTPS_API_NAONED_URL = "https://api.naonedbus.net/";

    @GET("traffics")
    Call<List<TrafficData>> getTraffic();
}
