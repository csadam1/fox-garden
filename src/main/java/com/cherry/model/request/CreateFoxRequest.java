package com.cherry.model.request;

import com.cherry.validation.ValidGender;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateFoxRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Species cannot be empty")
    private String species;

    @NotNull(message = "Gender cannot be empty")
    @ValidGender(message = "Gender value can be only MALE or FEMALE")
    private String gender;

    private String image;
}
