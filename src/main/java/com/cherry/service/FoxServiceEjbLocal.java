package com.cherry.service;

import com.cherry.model.entity.Fox;

import javax.ejb.Local;
import java.util.List;

@Local
public interface FoxServiceEjbLocal {
    /**
     * Returns all foxes available in the database.
     * @return List of Fox
     */
    List<Fox> getAllFoxes();
}
