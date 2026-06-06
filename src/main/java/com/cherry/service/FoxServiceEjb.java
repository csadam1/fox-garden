package com.cherry.service;

import com.cherry.dao.FoxDaoLocal;
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
}
