package cuit.xsgw.base.account.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by zhaoxiangbin on 2019/6/25 14:34
 * 460837364@qq.com
 */
public class AddressLevel implements Parcelable {
    private String name;
    private int code;
    private int level;

    public AddressLevel() {
    }

    protected AddressLevel(Parcel in) {
        name = in.readString();
        code = in.readInt();
        level = in.readInt();
    }

    public static final Creator<AddressLevel> CREATOR = new Creator<AddressLevel>() {
        @Override
        public AddressLevel createFromParcel(Parcel in) {
            return new AddressLevel(in);
        }

        @Override
        public AddressLevel[] newArray(int size) {
            return new AddressLevel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(code);
        dest.writeInt(level);
    }
}
