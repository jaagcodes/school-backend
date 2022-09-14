package com.ja.spring.data.jpa.service;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {

    public T register(T t) throws Exception;

    public Optional<T> findById(ID id) throws Exception;

    public List<T> listAll() throws Exception;

    public T update(T p) throws Exception;

    public void deleteEntity(ID id) throws  Exception;

}
