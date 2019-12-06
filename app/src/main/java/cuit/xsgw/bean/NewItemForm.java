package cuit.xsgw.bean;

import java.util.ArrayList;
import java.util.List;

public class NewItemForm<T> extends BaseForm {
    private List<T> list;

    public List<T> getList() {
        if (list == null) list = new ArrayList<>();
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
