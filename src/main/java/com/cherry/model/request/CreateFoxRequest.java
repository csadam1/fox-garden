package com.cherry.model.request;

import com.cherry.validation.ValidGender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Fox creation request")
public class CreateFoxRequest {

    @ApiModelProperty(value = "Name of the fox", example = "Foxy", required = true)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ApiModelProperty(value = "Specie of the fox", example = "Red Fox", required = true)
    @NotBlank(message = "Species cannot be empty")
    private String species;

    @ApiModelProperty(value = "Gender of the fox", example = "MALE", required = true)
    @NotNull(message = "Gender cannot be empty")
    @ValidGender(message = "Gender value can be only MALE or FEMALE")
    private String gender;

    @ApiModelProperty(value = "Image of the fox", example = "foxy.jpg")
    private String image;
}
