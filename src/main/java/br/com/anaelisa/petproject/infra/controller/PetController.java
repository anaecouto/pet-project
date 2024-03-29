package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.application.component.pet.service.PetService;
import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.infra.helper.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@ResponseBody
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<PetDTO>>> getPets() {
        List<PetDTO> petList = petService.getPetList();

        ApiResponse<List<PetDTO>> apiResponse = new ApiResponse<>("SUCCESS", petList, 200L, null);

        return ResponseEntity.status(200).body(apiResponse);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<PetDTO>> savePet(@Valid @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.savePet(petDTO);

        ApiResponse<PetDTO> apiResponse = new ApiResponse<>("SUCCESS", savedPet, 201L, null);

        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<PetDTO>> getPet(@PathVariable Long id) {
        PetDTO pet = petService.getPetById(id);

        ApiResponse<PetDTO> apiResponse = new ApiResponse<>("SUCCESS", pet, 200L, null);

        return ResponseEntity.status(200).body(apiResponse);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse<PetDTO>> updatePet(@Valid @RequestBody PetDTO petDTO) {

        PetDTO petUpdated = petService.updatePet(petDTO);

        ApiResponse<PetDTO> apiResponse = new ApiResponse<>("SUCCESS", petUpdated, 200L, null);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deletePet(@PathVariable Long id) {
        String petDeleted = petService.deletePet(id);

        ApiResponse<String> apiResponse = new ApiResponse<>("SUCCESS", petDeleted , 204L, null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
