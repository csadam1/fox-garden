package com.cherry.view;

import com.cherry.model.entity.Fox;
import com.cherry.model.request.CreateFoxRequest;
import com.cherry.service.FoxServiceEjbLocal;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@Named("foxFormBean")
@RequestScoped
public class FoxFormBean {

    private static final String FOX_CREATED_SUCCESSFULLY_WITH_ID_MESSAGE = "Fox created successfully with ID: {0}";
    private static final String ERROR_CREATING_FOX_MESSAGE = "Error creating fox";
    private static final String FOX_CREATION_SUCCESS_URL = "index.xhtml?success=true";

    private static final Logger logger = Logger.getLogger(FoxFormBean.class.getName());

    @EJB
    private FoxServiceEjbLocal foxServiceEjb;

    @Inject
    private FoxListBean foxListBean;

    private String name;
    private String species;
    private String gender;
    private String image;

    public void createFox() {
        try {
            CreateFoxRequest request = new CreateFoxRequest();
            request.setName(name);
            request.setSpecies(species);
            request.setGender(gender);
            request.setImage(image);

            Fox created = foxServiceEjb.createFox(request);
            foxListBean.loadFoxes();
            logger.log(Level.INFO, FOX_CREATED_SUCCESSFULLY_WITH_ID_MESSAGE, created.getId());

            clearForm();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(FOX_CREATION_SUCCESS_URL);
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_CREATING_FOX_MESSAGE, e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(ERROR_CREATING_FOX_MESSAGE + ": " + e.getMessage()));
        }
    }

    private void clearForm() {
        name = null;
        species = null;
        gender = null;
        image = null;
    }
}

