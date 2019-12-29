package cuit.xsgw.net.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MedicalUser implements Parcelable {
    private String id;
    private String areaCode;
    private String name;
    private String sex;
    private String idCard;
    private String phone;
    private String address;
    private String disease;
    private String blood;
    private String note;
    private List<Diseases> diseases;


    protected MedicalUser(Parcel in) {
        id = in.readString();
        areaCode = in.readString();
        name = in.readString();
        sex = in.readString();
        idCard = in.readString();
        phone = in.readString();
        address = in.readString();
        disease = in.readString();
        blood = in.readString();
        note = in.readString();
        diseases = in.createTypedArrayList(Diseases.CREATOR);
    }

    public static final Creator<MedicalUser> CREATOR = new Creator<MedicalUser>() {
        @Override
        public MedicalUser createFromParcel(Parcel in) {
            return new MedicalUser(in);
        }

        @Override
        public MedicalUser[] newArray(int size) {
            return new MedicalUser[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Diseases> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Diseases> diseases) {
        this.diseases = diseases;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(areaCode);
        parcel.writeString(name);
        parcel.writeString(sex);
        parcel.writeString(idCard);
        parcel.writeString(phone);
        parcel.writeString(address);
        parcel.writeString(disease);
        parcel.writeString(blood);
        parcel.writeString(note);
        parcel.writeTypedList(diseases);
    }
}
