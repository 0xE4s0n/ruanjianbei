package cuit.xsgw.bean;

import java.util.List;

/**
 * 单选框表格
 */
public class RadioForm extends BaseForm {
    private int spancount = 2;//列数(一排显示几个 默认为2)
    private List<RadioStringBean> radioData;

    public List<RadioStringBean> getRadioData() {
        return radioData;
    }

    public void setRadioData(List<RadioStringBean> radioData) {
        this.radioData = radioData;
    }

    public int getSpancount() {
        return spancount;
    }

    public void setSpancount(int spancount) {
        this.spancount = spancount;
    }
}
