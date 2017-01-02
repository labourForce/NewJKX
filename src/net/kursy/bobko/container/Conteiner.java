package net.kursy.bobko.container;

import net.kursy.bobko.entity.Entity;

import java.util.List;

public class Conteiner<T extends Entity> {

    private List<T> list;
    private int index = -1;

    public Conteiner(List<T> list) {
        this.list = list;
    }

    public int getLenght() {
        return list.size();
    }

    public void add(T e) {
        ++index;
        list.add(e);
    }

    public Entity getEntity() {

        return list.get(index--);
    }

    @Override
    public String toString() {

        String s = " ";
        for (Entity entity : list) {
            s += entity;
        }
        return s;
    }
}
