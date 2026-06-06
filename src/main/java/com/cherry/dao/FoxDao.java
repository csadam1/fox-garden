package com.cherry.dao;

import com.cherry.model.entity.Fox;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class FoxDao implements FoxDaoLocal {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Fox> findAll() {
        return entityManager.createQuery("SELECT f FROM Fox f", Fox.class).getResultList();
    }

    @Override
    public Optional<Fox> findById(int id) {
        return Optional.ofNullable(entityManager.find(Fox.class, id));
    }
}
