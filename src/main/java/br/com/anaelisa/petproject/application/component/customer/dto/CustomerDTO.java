package br.com.anaelisa.petproject.application.component.customer.dto;

import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    private Long id;
    private String username;
    private List<PetDTO> petList;
}
