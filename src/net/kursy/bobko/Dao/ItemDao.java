package net.kursy.bobko.Dao;


import net.kursy.bobko.container.Conteiner;
import net.kursy.bobko.entity.Entity;

public interface ItemDao<T extends Entity> {

    T readById(int id);

    int update(T t);

    Conteiner getAll();
}
