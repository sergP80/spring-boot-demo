package ua.edu.chmnu.ki.network.property.web;

import java.io.Serializable;

/**
 * C - create
 * R - read
 * U - update
 * D - delete
 */
public interface CrudApi<T, PK extends Serializable> {

    T getById(PK id);

    T create(T source);

    T update(PK id, T source);

    void deleteById(PK id);
}
