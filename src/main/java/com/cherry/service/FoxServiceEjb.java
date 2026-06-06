package com.cherry.service;

import com.cherry.dao.FoxDaoLocal;
import com.cherry.exception.FoxNotFoundException;
import com.cherry.model.entity.Fox;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class FoxServiceEjb implements FoxServiceEjbLocal {

    @EJB
    private FoxDaoLocal foxDao;

    @Override
    public List<Fox> getAllFoxes() {
        return foxDao.findAll();
    }

    @Override
    public Fox getFoxById(final int id) {
        return foxDao.findById(id)
                .orElseThrow(() -> new FoxNotFoundException(id));
    }
}
