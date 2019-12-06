package cuit.xsgw.bean;

/**
 * 日期表格
 */
public class DateForm extends BaseForm {
    private long curDate;//以秒为单位

    public long getCurDate() {
        return curDate;
    }

    public void setCurDate(long curDate) {
        this.curDate = curDate;
    }
}
