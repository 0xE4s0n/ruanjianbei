package cuit.xsgw.net.res;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 体质
 */
public class Corporeitie implements Parcelable {
    private String id;
    private String name;
    private String tag;

    public Corporeitie() {
    }

    protected Corporeitie(Parcel in) {
        id = in.readString();
        name = in.readString();
        tag = in.readString();
    }

    public static final Creator<Corporeitie> CREATOR = new Creator<Corporeitie>() {
        @Override
        public Corporeitie createFromParcel(Parcel in) {
            return new Corporeitie(in);
        }

        @Override
        public Corporeitie[] newArray(int size) {
            return new Corporeitie[size];
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(tag);
    }
}
