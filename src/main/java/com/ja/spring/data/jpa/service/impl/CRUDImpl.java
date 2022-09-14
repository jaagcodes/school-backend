package com.ja.spring.data.jpa.service.impl;

import com.ja.spring.data.jpa.repository.IGenericRepo;
import com.ja.spring.data.jpa.service.ICRUD;

import java.util.List;
import java.util.Optional;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    public abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T register(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public Optional<T> findById(ID id) throws Exception {
        return getRepo().findById(id);
    }

    @Override
    public List<T> listAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T update(T e) throws Exception {
        return getRepo().save(e);
    }

    @Override
    public void deleteEntity(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
