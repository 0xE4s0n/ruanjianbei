package cuit.xsgw.net.req;

/**
 * 药品
 */
public class Medical {
    private String name; // 药物名称
    private int rate; // 用量次数
    private String capacity; // 每次用量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
