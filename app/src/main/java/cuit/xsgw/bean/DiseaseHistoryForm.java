package cuit.xsgw.bean;

import java.util.List;

/**
 * 疾病史表单
 */
public class DiseaseHistoryForm extends BaseForm {
    private List<DiseaseHistoryBean> checkData;

    public List<DiseaseHistoryBean> getCheckData() {
        return checkData;
    }

    public void setCheckData(List<DiseaseHistoryBean> checkData) {
        this.checkData = checkData;
    }
}
