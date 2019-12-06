package cuit.xsgw.net.res;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private String id;
    private String questionId;
    private String content;
    private String detail;
    private int value;
    private boolean selected;//非接口字段

    public Answer() {
    }

    protected Answer(Parcel in) {
        id = in.readString();
        questionId = in.readString();
        content = in.readString();
        detail = in.readString();
        value = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(questionId);
        dest.writeString(content);
        dest.writeString(detail);
        dest.writeInt(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
