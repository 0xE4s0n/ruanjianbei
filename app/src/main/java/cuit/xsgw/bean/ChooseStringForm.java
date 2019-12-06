package cuit.xsgw.bean;

/**
 * 单个输入框表格
 */
public class ChooseStringForm extends BaseForm {
    private String curId;
    private String curValue;

    public String getCurId() {
        return curId;
    }

    public void setCurId(String curId) {
        this.curId = curId;
    }

    public String getCurValue() {
        return curValue;
    }

    public void setCurValue(String curValue) {
        this.curValue = curValue;
    }
}
