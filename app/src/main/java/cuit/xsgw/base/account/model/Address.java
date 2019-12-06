package cuit.xsgw.base.account.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 地址信息
 * created by zhaoxiangbin on 2019/6/25 14:30
 * 460837364@qq.com
 */
public class Address implements Parcelable {
    private String id;
    private String tagName;
    private String userName;
    private String phone;
    private int zoneCode;
    private String detailAddress;
    private int isDefault;
    private List<AddressLevel> addressLevels;
    private String parentArea;
    private String userId;
    private String createdAt;
    private String updatedAt;

    public Address() {
    }

    protected Address(Parcel in) {
        id = in.readString();
        tagName = in.readString();
        userName = in.readString();
        phone = in.readString();
        zoneCode = in.readInt();
        detailAddress = in.readString();
        isDefault = in.readInt();
        addressLevels = in.createTypedArrayList(AddressLevel.CREATOR);
        parentArea = in.readString();
        userId = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(int zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int isDefault() {
        return isDefault;
    }

    public void setDefault(int aDefault) {
        isDefault = aDefault;
    }

    public List<AddressLevel> getAddressLevels() {
        return addressLevels;
    }

    public void setAddressLevels(List<AddressLevel> addressLevels) {
        this.addressLevels = addressLevels;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tagName);
        dest.writeString(userName);
        dest.writeString(phone);
        dest.writeInt(zoneCode);
        dest.writeString(detailAddress);
        dest.writeInt(isDefault);
        dest.writeTypedList(addressLevels);
        dest.writeString(parentArea);
        dest.writeString(userId);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}
