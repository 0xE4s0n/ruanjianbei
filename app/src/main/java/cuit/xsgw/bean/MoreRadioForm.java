package cuit.xsgw.bean;

import java.util.List;

/**
 * 多个输入框表格
 */
public class MoreRadioForm extends BaseForm {
    private List<MoreRadioBean> moreRadioData;

    public List<MoreRadioBean> getMoreRadioData() {
        return moreRadioData;
    }

    public void setMoreRadioData(List<MoreRadioBean> moreRadioData) {
        this.moreRadioData = moreRadioData;
    }
}
