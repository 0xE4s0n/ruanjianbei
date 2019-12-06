package cuit.xsgw.bean;

/**
 * 单个输入框表格
 */
public class SingleEditForm extends BaseForm {
    private String unitData;//单位
    private int inputType;
    private String curValue;

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
