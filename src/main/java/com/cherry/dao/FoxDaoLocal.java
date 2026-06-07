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

    /**
     * Persists a Fox object in the database
     * @param fox Fox to be stored
     * @return the currently stored Fox
     */
    Fox save(Fox fox);

    /**
     * Deletes a Fox object from the database
     * @param fox the Fox to be deleted
     */
    void delete(Fox fox);
}
