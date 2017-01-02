package net.kursy.bobko.entity;


public class Brigade extends Entity {

    private String name;
    private boolean isActive;

    public Brigade() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
