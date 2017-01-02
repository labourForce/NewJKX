package net.kursy.bobko.entity;


public class TypeOfWorks extends Entity {

    private String type;
    private int cost;
    private Brigade brigade;

    public TypeOfWorks() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Brigade getBrigade() {
        return brigade;
    }

    public void setBrigade(Brigade brigade) {
        this.brigade = brigade;
    }

    @Override
    public String toString() {
        return type;
    }
}
