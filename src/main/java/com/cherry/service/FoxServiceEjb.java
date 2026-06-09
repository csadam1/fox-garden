package com.cherry.service;

import com.cherry.dao.FoxDaoLocal;
import com.cherry.exception.FoxNotFoundException;
import com.cherry.model.enumerate.Gender;
import com.cherry.model.request.CreateFoxRequest;
import com.cherry.model.entity.Fox;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Stateless
public class FoxServiceEjb implements FoxServiceEjbLocal {

    @EJB
    private FoxDaoLocal foxDao;

    @Override
    @TransactionAttribute
    public List<Fox> getAllFoxes() {
        return foxDao.findAll();
    }

    @Override
    @TransactionAttribute
    public Fox getFoxById(final int id) {
        return foxDao.findById((long) id)
                .orElseThrow(() -> new FoxNotFoundException(id));
    }

    @Override
    @TransactionAttribute
    public Fox createFox(final CreateFoxRequest request) {
        Fox fox = mapRequestToFox(request);
        return foxDao.save(fox);
    }

    @Override
    @TransactionAttribute
    public void deleteFoxById(int id) {
        Fox fox = getFoxById(id);
        foxDao.delete(fox);
    }

    private Fox mapRequestToFox(final CreateFoxRequest request) {
        return Fox.builder()
                .name(request.getName())
                .species(request.getSpecies())
                .gender(Gender.valueOf(request.getGender()))
                .image(request.getImage())
                .build();
    }
}
