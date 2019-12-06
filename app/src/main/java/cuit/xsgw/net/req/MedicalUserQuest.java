package cuit.xsgw.net.req;

import java.util.List;

public class MedicalUserQuest extends BaseRecordQuest {
    //基础信息
    private String idCard;//身份证
    private String pic;//照片
    private String name;//姓名
    private String ethnic;//民族
    private int census;//户籍类型 0户籍 1非户籍
    private String phone;//电话
    private String areaCode;//社区code
    private String address;//现住地址
    private String censusAddress;//户籍地址
    //更多信息
    private String employer;//工作单位
    private String contactName;//联系人姓名
    private String contactPhone;//联系人电话
    private int bloodType;//血型 1A 2B 3O 4AB 5不详
    private int bloodRh;//血型(RH) 1阳 2阴 3不详
    private int educationType;//文化程度 1研究生 2大学本科 3专科 4中专 5技工学校 6高中 7初中 8小学 9文盲 10不详
    private int jobType;//职业 1国家 2专业 3办事 4商业 5农 6生产 7军人 8不便 9无
    private int marriageType;//婚姻状况 1未婚 2已婚 3丧欧 4离婚 5未说明
    private List<Symptom> medicinePays;//医疗费用支付方式  1城镇职工2城镇居民3新农合4贫助5商业6全公费7全自费8其他
    private List<Symptom> medicineAllergies;//药物过敏史 1无2青霉素3磺胺4链霉素5其他
    private List<Symptom> userExposes;//暴露史 1无2化学品3毒物4射线5其他
    private List<Symptom> userBeforeDiseases;//以往疾病史 1无2高血压3糖尿病4冠心病5慢性6恶性7闹卒8严重9肺结核10肝炎11其他12职业病13其他
    private String operation;//手术 1无2有
    private String traumatic;//外伤 1无2有
    private String transfusion;//输血 1无2有
    private List<Symptom> familyFathers;//家族 父亲疾病史 1无2高血压3糖尿病4冠心病5慢性6恶性7闹卒8严重9肺结核10肝炎11先天畸形12其他
    private List<Symptom> familyMothers;//家族 母亲疾病史 1无2高血压3糖尿病4冠心病5慢性6恶性7闹卒8严重9肺结核10肝炎11先天畸形12其他
    private List<Symptom> familyBrothers;//家族 兄弟姐妹疾病史 1无2高血压3糖尿病4冠心病5慢性6恶性7闹卒8严重9肺结核10肝炎11先天畸形12其他
    private List<Symptom> familyChildren;//家族 子女疾病史 1无2高血压3糖尿病4冠心病5慢性6恶性7闹卒8严重9肺结核10肝炎11先天畸形12其他
    private String inheritance;//遗传 1无2有
    private List<Symptom> diseaseConditions;//残疾情况 1无2视力3听力4言语5肢体6智力7精神8其他
    private int kitchenDevice;//厨房排风设施 1无2油烟机3换气扇4烟囱
    private int fuel;//燃料类型 1液化气2煤3天然气4沼气5柴火
    private int water;//饮水 1自来水2净化3井水4湖河水5塘水6其他
    private int washroom;//厕所 1卫生2马桶3露天4一格5简易
    private int beast;//禽兽栏 1无2单设3室内4室外

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public int getCensus() {
        return census;
    }

    public void setCensus(int census) {
        this.census = census;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCensusAddress() {
        return censusAddress;
    }

    public void setCensusAddress(String censusAddress) {
        this.censusAddress = censusAddress;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getBloodType() {
        return bloodType;
    }

    public void setBloodType(int bloodType) {
        this.bloodType = bloodType;
    }

    public int getBloodRh() {
        return bloodRh;
    }

    public void setBloodRh(int bloodRh) {
        this.bloodRh = bloodRh;
    }

    public int getEducationType() {
        return educationType;
    }

    public void setEducationType(int educationType) {
        this.educationType = educationType;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public int getMarriageType() {
        return marriageType;
    }

    public void setMarriageType(int marriageType) {
        this.marriageType = marriageType;
    }

    public List<Symptom> getMedicinePays() {
        return medicinePays;
    }

    public void setMedicinePays(List<Symptom> medicinePays) {
        this.medicinePays = medicinePays;
    }

    public List<Symptom> getMedicineAllergies() {
        return medicineAllergies;
    }

    public void setMedicineAllergies(List<Symptom> medicineAllergies) {
        this.medicineAllergies = medicineAllergies;
    }

    public List<Symptom> getUserExposes() {
        return userExposes;
    }

    public void setUserExposes(List<Symptom> userExposes) {
        this.userExposes = userExposes;
    }

    public List<Symptom> getUserBeforeDiseases() {
        return userBeforeDiseases;
    }

    public void setUserBeforeDiseases(List<Symptom> userBeforeDiseases) {
        this.userBeforeDiseases = userBeforeDiseases;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTraumatic() {
        return traumatic;
    }

    public void setTraumatic(String traumatic) {
        this.traumatic = traumatic;
    }

    public String getTransfusion() {
        return transfusion;
    }

    public void setTransfusion(String transfusion) {
        this.transfusion = transfusion;
    }

    public List<Symptom> getFamilyFathers() {
        return familyFathers;
    }

    public void setFamilyFathers(List<Symptom> familyFathers) {
        this.familyFathers = familyFathers;
    }

    public List<Symptom> getFamilyMothers() {
        return familyMothers;
    }

    public void setFamilyMothers(List<Symptom> familyMothers) {
        this.familyMothers = familyMothers;
    }

    public List<Symptom> getFamilyBrothers() {
        return familyBrothers;
    }

    public void setFamilyBrothers(List<Symptom> familyBrothers) {
        this.familyBrothers = familyBrothers;
    }

    public List<Symptom> getFamilyChildren() {
        return familyChildren;
    }

    public void setFamilyChildren(List<Symptom> familyChildren) {
        this.familyChildren = familyChildren;
    }

    public String getInheritance() {
        return inheritance;
    }

    public void setInheritance(String inheritance) {
        this.inheritance = inheritance;
    }

    public List<Symptom> getDiseaseConditions() {
        return diseaseConditions;
    }

    public void setDiseaseConditions(List<Symptom> diseaseConditions) {
        this.diseaseConditions = diseaseConditions;
    }

    public int getKitchenDevice() {
        return kitchenDevice;
    }

    public void setKitchenDevice(int kitchenDevice) {
        this.kitchenDevice = kitchenDevice;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getWashroom() {
        return washroom;
    }

    public void setWashroom(int washroom) {
        this.washroom = washroom;
    }

    public int getBeast() {
        return beast;
    }

    public void setBeast(int beast) {
        this.beast = beast;
    }
}
