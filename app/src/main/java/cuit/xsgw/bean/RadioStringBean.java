package cuit.xsgw.bean;

public class RadioStringBean {
    private int id;
    private String name;
    private boolean selected;
    private boolean needDetail;
    private String detail;

    public RadioStringBean(int id, String name, boolean selected, boolean needDetail) {
        this.id = id;
        this.name = name;
        this.selected = selected;
        this.needDetail = needDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isNeedDetail() {
        return needDetail;
    }

    public void setNeedDetail(boolean needDetail) {
        this.needDetail = needDetail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
