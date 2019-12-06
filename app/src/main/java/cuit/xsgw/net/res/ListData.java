package cuit.xsgw.net.res;

import java.util.List;

/**
 * created by zhaoxiangbin on 2019/7/23 14:34
 * 460837364@qq.com
 */
public class ListData<T> {
    private int count;
    private List<T> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
