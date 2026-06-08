package com.cherry.view;

import com.cherry.exception.FoxNotFoundException;
import com.cherry.model.entity.Fox;
import com.cherry.service.FoxServiceEjbLocal;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@Named("foxListBean")
@SessionScoped
public class FoxListBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String ERROR_LOADING_FOX_MESSAGE = "Error loading fox";
    private static final String FOX_NOT_FOUND_MESSAGE = "Fox not found";
    private static final String ERROR_DELETING_FOX_MESSAGE = "Error deleting fox";
    private static final String FOX_DELETED_SUCCESSFULLY_MESSAGE = "Fox deleted successfully!";

    private static final Logger logger = Logger.getLogger(FoxListBean.class.getName());

    @EJB
    private FoxServiceEjbLocal foxServiceEjb;

    private List<Fox> foxes;
    private Fox selectedFox;
    private String searchText;
    private String successMessage;
    private String errorMessage;

    @PostConstruct
    public void init() {
        loadFoxes();
    }

    public void loadFoxes() {
        try {
            foxes = foxServiceEjb.getAllFoxes();
            successMessage = null;
            errorMessage = null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_LOADING_FOX_MESSAGE, e);
            errorMessage = ERROR_LOADING_FOX_MESSAGE + ": " + e.getMessage();
        }
    }

    public void deleteFox(Fox fox) {
        try {
            if (fox != null && fox.getId() != null) {
                foxServiceEjb.deleteFoxById(Math.toIntExact(fox.getId()));
                loadFoxes();
                successMessage = FOX_DELETED_SUCCESSFULLY_MESSAGE;
            }
        } catch (FoxNotFoundException e) {
            logger.log(Level.WARNING, FOX_NOT_FOUND_MESSAGE, e);
            errorMessage = FOX_NOT_FOUND_MESSAGE;
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_DELETING_FOX_MESSAGE, e);
            errorMessage = ERROR_DELETING_FOX_MESSAGE + ": " + e.getMessage();
        }
    }

    public void clearMessages() {
        successMessage = null;
        errorMessage = null;
    }
}

