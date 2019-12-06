package cuit.xsgw.net.res;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CNMediQuestion implements Parcelable {
    private String id;
    private String content;
    private String valueType;
    private List<Corporeitie> corporeities;
    private List<Answer> answers;

    public CNMediQuestion() {
    }

    protected CNMediQuestion(Parcel in) {
        id = in.readString();
        content = in.readString();
        valueType = in.readString();
        corporeities = in.createTypedArrayList(Corporeitie.CREATOR);
        answers = in.createTypedArrayList(Answer.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(content);
        dest.writeString(valueType);
        dest.writeTypedList(corporeities);
        dest.writeTypedList(answers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CNMediQuestion> CREATOR = new Creator<CNMediQuestion>() {
        @Override
        public CNMediQuestion createFromParcel(Parcel in) {
            return new CNMediQuestion(in);
        }

        @Override
        public CNMediQuestion[] newArray(int size) {
            return new CNMediQuestion[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public List<Corporeitie> getCorporeities() {
        return corporeities;
    }

    public void setCorporeities(List<Corporeitie> corporeities) {
        this.corporeities = corporeities;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
