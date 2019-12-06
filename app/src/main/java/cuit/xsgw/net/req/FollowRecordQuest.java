package cuit.xsgw.net.req;

import java.util.List;

public class FollowRecordQuest extends BaseRecordQuest {
    private long visitTime; // 随访日期 单位为 秒

    private int method; // 随访方式 1:门诊 2:家庭 3:电话

    private List<Symptom> recordSymptoms; // 症状

    private float pressureMin; // 血压 -- 收缩压
    private float pressureMax; // 血压 -- 舒张压

    private float height; // 身高

    private float weight; // 当前体重
    private float goalWeight; // 目标体重

    private String other; // 其它

    private float daySmoke; // 当前日吸烟量
    private float goalDaySmoke; // 目标日吸烟量

    private float dayWine; // 当前日饮酒量
    private float goalDayWine; // 目标日饮酒量

    private float sportRate; // 运动情况 次/周
    private float sportTime; // 运动情况 分钟/次
    private float goalSportRate; // 目标运动情况 次/周
    private float goalSportTime; // 目标运动情况 分钟/次

    private int mental; // 心理调整 1:良好 2:一般 3:差
    private int followDoc; // 遵医行为 1:良好 2:一般 3:差

    private String assistCheck; // 辅助检查

    private List<Medical> recordMedicines; // 药品
    private int rely; // 服药依从性 1:规律 2:间断 3:不服药
    private String badness; // 药物不良反应 无/有
    private int classify; // 此次随访分类 1:控制满意 2:控制不满意 3:不良反应 4:并发症

    private String transferReason; // 转诊原因
    private String transferOrg; // 转诊机构及其科别

    private long nextTime; // 下次随访日期 单位为 秒

    public long getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(long visitTime) {
        this.visitTime = visitTime;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public List<Symptom> getRecordSymptoms() {
        return recordSymptoms;
    }

    public void setRecordSymptoms(List<Symptom> recordSymptoms) {
        this.recordSymptoms = recordSymptoms;
    }

    public float getPressureMin() {
        return pressureMin;
    }

    public void setPressureMin(float pressureMin) {
        this.pressureMin = pressureMin;
    }

    public float getPressureMax() {
        return pressureMax;
    }

    public void setPressureMax(float pressureMax) {
        this.pressureMax = pressureMax;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(float goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public float getDaySmoke() {
        return daySmoke;
    }

    public void setDaySmoke(float daySmoke) {
        this.daySmoke = daySmoke;
    }

    public float getGoalDaySmoke() {
        return goalDaySmoke;
    }

    public void setGoalDaySmoke(float goalDaySmoke) {
        this.goalDaySmoke = goalDaySmoke;
    }

    public float getDayWine() {
        return dayWine;
    }

    public void setDayWine(float dayWine) {
        this.dayWine = dayWine;
    }

    public float getGoalDayWine() {
        return goalDayWine;
    }

    public void setGoalDayWine(float goalDayWine) {
        this.goalDayWine = goalDayWine;
    }

    public float getSportRate() {
        return sportRate;
    }

    public void setSportRate(float sportRate) {
        this.sportRate = sportRate;
    }

    public float getSportTime() {
        return sportTime;
    }

    public void setSportTime(float sportTime) {
        this.sportTime = sportTime;
    }

    public float getGoalSportRate() {
        return goalSportRate;
    }

    public void setGoalSportRate(float goalSportRate) {
        this.goalSportRate = goalSportRate;
    }

    public float getGoalSportTime() {
        return goalSportTime;
    }

    public void setGoalSportTime(float goalSportTime) {
        this.goalSportTime = goalSportTime;
    }

    public int getMental() {
        return mental;
    }

    public void setMental(int mental) {
        this.mental = mental;
    }

    public int getFollowDoc() {
        return followDoc;
    }

    public void setFollowDoc(int followDoc) {
        this.followDoc = followDoc;
    }

    public String getAssistCheck() {
        return assistCheck;
    }

    public void setAssistCheck(String assistCheck) {
        this.assistCheck = assistCheck;
    }

    public List<Medical> getRecordMedicines() {
        return recordMedicines;
    }

    public void setRecordMedicines(List<Medical> recordMedicines) {
        this.recordMedicines = recordMedicines;
    }

    public int getRely() {
        return rely;
    }

    public void setRely(int rely) {
        this.rely = rely;
    }

    public String getBadness() {
        return badness;
    }

    public void setBadness(String badness) {
        this.badness = badness;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public String getTransferOrg() {
        return transferOrg;
    }

    public void setTransferOrg(String transferOrg) {
        this.transferOrg = transferOrg;
    }

    public long getNextTime() {
        return nextTime;
    }

    public void setNextTime(long nextTime) {
        this.nextTime = nextTime;
    }
}
