package cuit.xsgw.base.account.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by zhaoxiangbin on 2019/6/25 14:34
 * 460837364@qq.com
 */
public class Area implements Parcelable {
    private int code_;
    private int pid_;
    private int level_;
    private String name_;
    private int memoizedIsInitialized;
    private int memoizedSize;
    private int memoizedHashCode;

    protected Area(Parcel in) {
        code_ = in.readInt();
        pid_ = in.readInt();
        level_ = in.readInt();
        name_ = in.readString();
        memoizedIsInitialized = in.readInt();
        memoizedSize = in.readInt();
        memoizedHashCode = in.readInt();
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel in) {
            return new Area(in);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

    public int getCode_() {
        return code_;
    }

    public void setCode_(int code_) {
        this.code_ = code_;
    }

    public int getPid_() {
        return pid_;
    }

    public void setPid_(int pid_) {
        this.pid_ = pid_;
    }

    public int getLevel_() {
        return level_;
    }

    public void setLevel_(int level_) {
        this.level_ = level_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public int getMemoizedIsInitialized() {
        return memoizedIsInitialized;
    }

    public void setMemoizedIsInitialized(int memoizedIsInitialized) {
        this.memoizedIsInitialized = memoizedIsInitialized;
    }

    public int getMemoizedSize() {
        return memoizedSize;
    }

    public void setMemoizedSize(int memoizedSize) {
        this.memoizedSize = memoizedSize;
    }

    public int getMemoizedHashCode() {
        return memoizedHashCode;
    }

    public void setMemoizedHashCode(int memoizedHashCode) {
        this.memoizedHashCode = memoizedHashCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code_);
        dest.writeInt(pid_);
        dest.writeInt(level_);
        dest.writeString(name_);
        dest.writeInt(memoizedIsInitialized);
        dest.writeInt(memoizedSize);
        dest.writeInt(memoizedHashCode);
    }
}
