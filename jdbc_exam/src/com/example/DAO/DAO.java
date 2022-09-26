package com.example.DAO;

public interface DAO<T> {


    void getAll();

    void get(T t);

    void getByID(int id);

    void add(T t);

    void update(T t);

    void delete(int id);
}