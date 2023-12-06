package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.domain.component.PetComponent;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.handler.ApiResponse;
import br.com.anaelisa.petproject.infra.dtos.UpdatePetDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@ResponseBody
public class PetController {

    @Autowired
    private PetComponent petComponent;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<PetEntity>>> getPets() {
        List<PetEntity> petList = petComponent.getPetList();

        ApiResponse<List<PetEntity>> apiResponse = new ApiResponse<>("SUCCESS", petList, 200L, null);

        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<PetEntity>> savePet(@Valid @RequestBody PetEntity petEntity) {
        PetEntity savedPet = petComponent.savePet(petEntity);

        ApiResponse<PetEntity> apiResponse = new ApiResponse<>("SUCCESS", savedPet, 201L, null);

        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<PetEntity>> getPet(@PathVariable Long id) {
        PetEntity pet = petComponent.getPetById(id);

        ApiResponse<PetEntity> apiResponse = new ApiResponse<>("SUCCESS", pet, 200L, null);

        return ResponseEntity.status(200).body(apiResponse);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<PetEntity>> updatePet(@PathVariable Long id, @Valid @RequestBody UpdatePetDTO updatePetDTO) {
        PetEntity existingPet = petComponent.getPetById(id);

        PetEntity petUpdated = petComponent.updatePet(updatePetDTO, existingPet);

        ApiResponse<PetEntity> apiResponse = new ApiResponse<>("SUCCESS", petUpdated, 200L, null);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deletePet(@PathVariable Long id) {
        String petDeleted = petComponent.deletePet(id);

        ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", petDeleted , 204L, null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
