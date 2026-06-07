package com.cherry.service;

import com.cherry.model.request.CreateFoxRequest;
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

    /**
     * Returns a Fox
     * @param id id of a Fox
     * @return Fox object
     */
    Fox getFoxById(int id);

    /**
     * Adds the new Fox to the database
     * @param request contains the properties of the fox
     * @return the created Fox
     */
    Fox createFox(CreateFoxRequest request);

    /**
     * Deletes a Fox from the database
     * @param id id of the Fox
     */
    void deleteFoxById(int id);
}
