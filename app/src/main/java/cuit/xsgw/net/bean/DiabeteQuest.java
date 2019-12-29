package cuit.xsgw.net.bean;

/**
 * 糖尿病随访记录表
 */
public class DiabeteQuest extends FollowRecordQuest {
    private String diabetesRecordId; // 糖尿病随访记录id  新建时候没有、修改时候带上
    private int footPulsation; // 足背动脉搏动 10:正常 20:减弱双侧 21:减弱左侧 22:减弱右侧 30:消失双侧 31:消失左侧 32:消失右侧
    private float stapleFood; // 主食
    private float goalStapleFood; // 目标主食
    private float limosis; // 空腹血糖值
    private float sugarBloodRed; // 糖化血红蛋白
    private long checkTime; // 检查日期 单位为 秒
    private int lowSugarResponse; // 低血糖反应 1:无 2:偶尔 3:频繁
    private String insulinClass; // 胰岛素种类
    private String insulinMedicine; // 胰岛素用法用量

    public String getDiabetesRecordId() {
        return diabetesRecordId;
    }

    public void setDiabetesRecordId(String diabetesRecordId) {
        this.diabetesRecordId = diabetesRecordId;
    }

    public int getFootPulsation() {
        return footPulsation;
    }

    public void setFootPulsation(int footPulsation) {
        this.footPulsation = footPulsation;
    }

    public float getStapleFood() {
        return stapleFood;
    }

    public void setStapleFood(float stapleFood) {
        this.stapleFood = stapleFood;
    }

    public float getGoalStapleFood() {
        return goalStapleFood;
    }

    public void setGoalStapleFood(float goalStapleFood) {
        this.goalStapleFood = goalStapleFood;
    }

    public float getLimosis() {
        return limosis;
    }

    public void setLimosis(float limosis) {
        this.limosis = limosis;
    }

    public float getSugarBloodRed() {
        return sugarBloodRed;
    }

    public void setSugarBloodRed(float sugarBloodRed) {
        this.sugarBloodRed = sugarBloodRed;
    }

    public long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(long checkTime) {
        this.checkTime = checkTime;
    }

    public int getLowSugarResponse() {
        return lowSugarResponse;
    }

    public void setLowSugarResponse(int lowSugarResponse) {
        this.lowSugarResponse = lowSugarResponse;
    }

    public String getInsulinClass() {
        return insulinClass;
    }

    public void setInsulinClass(String insulinClass) {
        this.insulinClass = insulinClass;
    }

    public String getInsulinMedicine() {
        return insulinMedicine;
    }

    public void setInsulinMedicine(String insulinMedicine) {
        this.insulinMedicine = insulinMedicine;
    }
}
