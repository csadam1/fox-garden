package com.cherry.dao;

import com.cherry.model.entity.Fox;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface FoxDaoLocal {
    /**
     * Queries all Foxes from the database
     * @return List of Foxes
     */
    List<Fox> findAll();

    /**
     * Queries Fox from the database
     * @param id id of Fox
     * @return Fox object
     */
    Optional<Fox> findById(int id);
}
