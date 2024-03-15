package br.com.anaelisa.petproject.application.component.customer.dto;

import br.com.anaelisa.petproject.application.component.auth.enums.EnumRoles;
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
    private Long id;
    private String username;
    private EnumRoles role;
    private List<PetDTO> petList;
}
