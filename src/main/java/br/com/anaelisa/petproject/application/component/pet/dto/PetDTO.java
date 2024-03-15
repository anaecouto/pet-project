package br.com.anaelisa.petproject.application.component.pet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String breed;
}
