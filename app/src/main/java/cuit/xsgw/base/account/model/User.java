package cuit.xsgw.base.account.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * TODO: please input the description for this class.
 * <p>
 * notes:手机注册、第三方登陆、获取用户信息 全部用这个对象作Response
 */

public class User implements Parcelable {

    private String userId;
    private String userName;//用户名
    private String userHeadUrl;//头像URL
    private String acc_name; //帐户名/手机号/openid
    private long createTime;
    public String h5token;
    public String im_id;
    public String im_token;
    private String phone;//手机号
    private String token;
    private String nikename;//昵称

    private String overview;//简介
    private String profession; //职业
    private String real_name;//真实姓名

    private Address address;//收货地址
    private boolean isAdmin;

    public User() {
    }

    protected User(Parcel in) {
        userId = in.readString();
        userName = in.readString();
        userHeadUrl = in.readString();
        acc_name = in.readString();
        createTime = in.readLong();
        h5token = in.readString();
        im_id = in.readString();
        im_token = in.readString();
        phone = in.readString();
        token = in.readString();
        nikename = in.readString();
        overview = in.readString();
        profession = in.readString();
        real_name = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
        isAdmin = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getH5token() {
        return h5token;
    }

    public void setH5token(String h5token) {
        this.h5token = h5token;
    }

    public String getIm_id() {
        return im_id;
    }

    public void setIm_id(String im_id) {
        this.im_id = im_id;
    }

    public String getIm_token() {
        return im_token;
    }

    public void setIm_token(String im_token) {
        this.im_token = im_token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        if (TextUtils.isEmpty(token)) token = "empty";
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(userName);
        parcel.writeString(userHeadUrl);
        parcel.writeString(acc_name);
        parcel.writeLong(createTime);
        parcel.writeString(h5token);
        parcel.writeString(im_id);
        parcel.writeString(im_token);
        parcel.writeString(phone);
        parcel.writeString(token);
        parcel.writeString(nikename);
        parcel.writeString(overview);
        parcel.writeString(profession);
        parcel.writeString(real_name);
        parcel.writeParcelable(address, i);
        parcel.writeByte((byte) (isAdmin ? 1 : 0));
    }
}
