package cuit.xsgw.bean;

import java.util.List;

/**
 * 多个输入框表格
 */
public class MoreEditForm extends BaseForm {
    private List<MoreEditBean> moreEditData;

    public List<MoreEditBean> getMoreEditData() {
        return moreEditData;
    }

    public void setMoreEditData(List<MoreEditBean> moreEditData) {
        this.moreEditData = moreEditData;
    }
}
