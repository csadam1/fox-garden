package com.cherry.model.request;

import com.cherry.validation.ValidGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
