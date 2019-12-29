package cuit.xsgw.net.bean;

public class BaseRecordQuest {
    private String userId; // 病人id
    private String doctorId; // 医生id
    private String signatureId; // 病人签名的图片id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }
}
