package cuit.xsgw.bean;

public class ChooseAreaForm extends BaseForm {
    private String curAreaCode;
    private String curAreaName;//xx省,xx市,xx区县,xx镇,...

    public String getCurAreaCode() {
        return curAreaCode;
    }

    public void setCurAreaCode(String curAreaCode) {
        this.curAreaCode = curAreaCode;
    }

    public String getCurAreaName() {
        return curAreaName;
    }

    public void setCurAreaName(String curAreaName) {
        this.curAreaName = curAreaName;
    }
}
