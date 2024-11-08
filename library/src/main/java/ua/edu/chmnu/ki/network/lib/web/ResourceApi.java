package ua.edu.chmnu.ki.network.lib.web;

import java.io.Serializable;
import java.util.List;

public interface ResourceApi<T, PK extends Serializable> {

    T getById(PK id);

    List<T> getAll();

    T create(T source);

    T update(PK id, T source);
}
