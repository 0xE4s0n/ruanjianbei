package cuit.xsgw.bean;

import java.util.List;

/**
 * 多选框表格
 */
public class CheckboxForm extends BaseForm {
    private int spancount = 2;//列数(一排显示几个 默认为2)
    private List<CheckBoxBean> checkData;
    private String otherValue;

    public List<CheckBoxBean> getCheckData() {
        return checkData;
    }

    public void setCheckData(List<CheckBoxBean> checkData) {
        this.checkData = checkData;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    public int getSpancount() {
        return spancount;
    }

    public void setSpancount(int spancount) {
        this.spancount = spancount;
    }
}
