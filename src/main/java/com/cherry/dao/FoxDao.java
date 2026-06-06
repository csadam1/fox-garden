package com.cherry.dao;

import com.cherry.model.entity.Fox;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FoxDao implements FoxDaoLocal {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Fox> findAll() {
        return entityManager.createQuery("SELECT f FROM Fox f", Fox.class).getResultList();
    }
}
