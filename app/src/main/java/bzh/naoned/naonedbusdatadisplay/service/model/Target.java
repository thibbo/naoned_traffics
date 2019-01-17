package bzh.naoned.naonedbusdatadisplay.service.model;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Target extends RealmObject {
    @SerializedName("routeCode")
    private String mRouteCode;
    @SerializedName("routeLabel")
    private String mRouteLabel;
    @SerializedName("routeColor")
    private String mRouteColor;

    public String getRouteCode() {
        return mRouteCode;
    }

    public String getRouteLabel() {
        return mRouteLabel;
    }

    public int getRouteColor() {
        return Color.parseColor(mRouteColor);
    }
}
