package cuit.xsgw.bean;

public class DiseaseHistoryBean {
    private String name;
    private long sureTime;//确诊时间
    private String subName;//疾病名字
    private String subValue;
    private boolean selected;

    public DiseaseHistoryBean(String name, String subName, boolean selected) {
        this.name = name;
        this.subName = subName;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getSureTime() {
        return sureTime;
    }

    public void setSureTime(long sureTime) {
        this.sureTime = sureTime;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }
}
