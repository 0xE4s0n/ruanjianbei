package cuit.xsgw.bean;

public class MoreEditBean {
    private String name;
    private String unitData;//单位
    private int inputType;
    private String curValue;

    public MoreEditBean(String name, String unitData, int inputType) {
        this.name = name;
        this.unitData = unitData;
        this.inputType = inputType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitData() {
        return unitData;
    }

    public void setUnitData(String unitData) {
        this.unitData = unitData;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public String getCurValue() {
        return curValue;
    }

    public void setCurValue(String curValue) {
        this.curValue = curValue;
    }
}
