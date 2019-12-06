package cuit.xsgw.bean;

import java.util.List;

public class MoreRadioBean {
    private String name;
    private List<RadioStringBean> list;

    public MoreRadioBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RadioStringBean> getList() {
        return list;
    }

    public void setList(List<RadioStringBean> list) {
        this.list = list;
    }
}
