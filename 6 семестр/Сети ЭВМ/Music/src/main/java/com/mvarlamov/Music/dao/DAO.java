package com.mvarlamov.Music.dao;

public interface DAO<T> {
    T get();
    void update(T data);
    void delete(T data);
}
