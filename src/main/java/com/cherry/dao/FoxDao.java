package com.cherry.dao;

import com.cherry.model.entity.Fox;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class FoxDao implements FoxDaoLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @TransactionAttribute
    public List<Fox> findAll() {
        return entityManager.createQuery("SELECT f FROM Fox f", Fox.class).getResultList();
    }

    @Override
    @TransactionAttribute
    public Optional<Fox> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Fox.class, id));
    }

    @Override
    @TransactionAttribute
    public Optional<Fox> findOneWithEmptyImage() {
        List<Fox> list = entityManager.createQuery(
                "SELECT f FROM Fox f WHERE f.image IS NULL OR f.image = ''", Fox.class)
                .setMaxResults(1)
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    @TransactionAttribute
    public Fox save(Fox fox) {
        return entityManager.merge(fox);
    }

    @Override
    @TransactionAttribute
    public void delete(Fox fox) {
        entityManager.remove(fox);
    }
}
