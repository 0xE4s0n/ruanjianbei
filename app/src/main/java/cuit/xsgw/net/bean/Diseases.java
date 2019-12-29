package cuit.xsgw.net.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 疾病
 */
public class Diseases implements Parcelable {
    private String id;
    private String name;
    private String shortName;

    protected Diseases(Parcel in) {
        id = in.readString();
        name = in.readString();
        shortName = in.readString();
    }

    public static final Creator<Diseases> CREATOR = new Creator<Diseases>() {
        @Override
        public Diseases createFromParcel(Parcel in) {
            return new Diseases(in);
        }

        @Override
        public Diseases[] newArray(int size) {
            return new Diseases[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(shortName);
    }
}
