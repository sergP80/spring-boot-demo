package ua.edu.chmnu.ki.network.lib.web;

import java.io.Serializable;

public interface CrudApi<T, PK extends Serializable> {

    T getById(PK id);

    T create(T source);

    T update(PK id, T source);

    void deleteById(PK id);
}
