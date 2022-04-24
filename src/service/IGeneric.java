package service;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();
    void save(T t);
    void deleteById(int id);
    T findById(int id);
    void editById(T t);
}
