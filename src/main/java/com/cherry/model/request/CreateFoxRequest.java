package com.cherry.model.request;

import com.cherry.validation.ValidGender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Fox creation request")
public class CreateFoxRequest {

    @Schema(description = "Name of the fox", example = "Foxy", required = true)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Schema(description = "Species of the fox", example = "Red Fox", required = true)
    @NotBlank(message = "Species cannot be empty")
    private String species;

    @Schema(description = "Gender of the fox", example = "MALE", required = true)
    @NotNull(message = "Gender cannot be empty")
    @ValidGender(message = "Gender value can be only MALE or FEMALE")
    private String gender;

    @Schema(description = "Image filename of the fox", example = "foxy.jpg")
    private String image;
}
