package cuit.xsgw.bean;

public class TizhiBean {
    private int id;
    private String name;//体质名称
    private String score;//体质得分
    private int result;//体质结果 0不是   1是   2倾向是

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
