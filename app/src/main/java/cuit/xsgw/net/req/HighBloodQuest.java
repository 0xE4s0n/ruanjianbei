package cuit.xsgw.net.req;

/**
 * 高血压随访记录表
 */
public class HighBloodQuest extends FollowRecordQuest {
    private String bloodRecordId; // 高血压随访记录id  新建时候没有、修改时候带上
    private float heartRate; // 心率
    private int salt; // 摄盐情况 1:轻 2:中 3:重
    private int goalSalt; // 目标摄盐情况 1:轻 2:中 3:重

    public String getBloodRecordId() {
        return bloodRecordId;
    }

    public void setBloodRecordId(String bloodRecordId) {
        this.bloodRecordId = bloodRecordId;
    }

    public float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(float heartRate) {
        this.heartRate = heartRate;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public int getGoalSalt() {
        return goalSalt;
    }

    public void setGoalSalt(int goalSalt) {
        this.goalSalt = goalSalt;
    }
}
