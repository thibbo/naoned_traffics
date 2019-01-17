package bzh.naoned.naonedbusdatadisplay.service.model;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;
import io.realm.RealmObject;

public class TrafficData extends RealmObject {
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("dateStart")
    private Date mDate;
    @SerializedName("sourceCode")
    private String mSourceCode;
    @SerializedName("sourceLabel")
    private String mSourceLabel;
    @SerializedName("sourcePicture")
    private String mSourcePicture;
    @SerializedName("targets")
    private RealmList<Target> mTargets;

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getDate() {
        return new java.text.SimpleDateFormat("dd/MM/yyyy à HH:mm", Locale.getDefault()).format(mDate);
    }

    public String getSourceCode() {
        return mSourceCode;
    }

    public String getSourceLabel() {
        return mSourceLabel;
    }

    public String getSourcePicture() {
        return mSourcePicture;
    }

    public RealmList<Target> getTargets() {
        return mTargets;
    }
}
