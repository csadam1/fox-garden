package com.cherry.dao;

import com.cherry.model.entity.Fox;

import javax.ejb.Local;
import java.util.List;

@Local
public interface FoxDaoLocal {
    /**
     * Queries all Foxes from the database
     * @return List of Foxes
     */
    List<Fox> findAll();
}
