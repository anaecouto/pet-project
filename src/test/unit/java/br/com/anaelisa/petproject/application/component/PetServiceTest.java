package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.component.pet.service.PetService;
import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.application.component.user.service.AuthenticatedUserService;
import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.domain.entity.UserEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    PetRepository petRepository;

    @Mock
    AuthenticatedUserService authenticatedUserService;

    @InjectMocks
    PetService petService;

    @Test
    void shouldListAllPets() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        List<PetEntity> petEntityList = List.of(
                new PetEntity(1L, "pet 1", "type 1", "breed 1", userEntity),
                new PetEntity(2L, "pet 2", "type 2", "breed 2", userEntity));

        when(authenticatedUserService.getLoggedUser()).thenReturn(userEntity);
        when(petRepository.findAllByOwnerId(userEntity.getId())).thenReturn(petEntityList);

        List<PetDTO> petEntityListResult = petService.getPetList();

        Assertions.assertEquals(Long.valueOf(1L), petEntityListResult.get(0).getId());
        Assertions.assertEquals("pet 1", petEntityListResult.get(0).getName());
        Assertions.assertEquals("type 1", petEntityListResult.get(0).getType());
        Assertions.assertEquals("breed 1", petEntityListResult.get(0).getBreed());
    }

    @Test
    void shouldUpdatePet() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        PetDTO updatedPetDTO = PetDTO.builder()
                .id(1L)
                .name("New name")
                .type("Dog")
                .breed("Golden Retriever")
                .build();

        PetEntity existingPetEntity = new PetEntity();
        existingPetEntity.setId(1L);
        existingPetEntity.setName("Old name");
        existingPetEntity.setType("Dog");
        existingPetEntity.setBreed("Golden Retriever");
        existingPetEntity.setOwner(userEntity);

        PetEntity savedPetEntity = new PetEntity();
        savedPetEntity.setId(1L);
        savedPetEntity.setName("New name");
        savedPetEntity.setType("Dog");
        savedPetEntity.setBreed("Golden Retriever");
        savedPetEntity.setOwner(userEntity);

        when(authenticatedUserService.getLoggedUser()).thenReturn(userEntity);
        when(petRepository.findByIdAndOwnerId(existingPetEntity.getId(), userEntity.getId()))
                .thenReturn(Optional.of(existingPetEntity));
        when(petRepository.save(existingPetEntity)).thenReturn(savedPetEntity);

        PetDTO returnedPetDTO = petService.updatePet(updatedPetDTO);

        Assertions.assertEquals(returnedPetDTO.getId(), updatedPetDTO.getId());
        Assertions.assertEquals(returnedPetDTO.getName(), updatedPetDTO.getName());
        Assertions.assertEquals(returnedPetDTO.getType(), updatedPetDTO.getType());
        Assertions.assertEquals(returnedPetDTO.getBreed(), updatedPetDTO.getBreed());
        verify(petRepository, times(1)).findByIdAndOwnerId(1L, 1L);
        verify(petRepository, times(1)).save(existingPetEntity);
    }

    @Test
    void shouldSavePet() {
        PetEntity petEntity = new PetEntity();
        petEntity.setId(1L);
        petEntity.setName("name");

        PetDTO petDTO = PetDTO.builder()
                .name("name")
                .build();

        when(petRepository.save(any(PetEntity.class))).thenReturn(petEntity);

        PetDTO pet = petService.savePet(petDTO);

        Assertions.assertEquals(Long.valueOf(1L), pet.getId());
        Assertions.assertEquals("name", pet.getName());
    }

    @Test
    void shouldThrowErrorIfPetNotExists() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        when(authenticatedUserService.getLoggedUser()).thenReturn(userEntity);
        when(petRepository.findByIdAndOwnerId(1L, userEntity.getId())).thenReturn(Optional.empty());

        PetNotFoundException exception = assertThrows(PetNotFoundException.class, () -> petService.getPetById(1L));

        Assertions.assertEquals("Pet not found", exception.getMessage());
    }

    @Test
    void shouldFindPet() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        PetEntity petEntity = new PetEntity();
        petEntity.setId(1L);
        petEntity.setOwner(userEntity);

        when(authenticatedUserService.getLoggedUser()).thenReturn(userEntity);
        when(petRepository.findByIdAndOwnerId(petEntity.getId(), userEntity.getId()))
                .thenReturn(Optional.of(petEntity));

        PetDTO petDTO = petService.getPetById(1L);

        Assertions.assertEquals(Long.valueOf(1L), petDTO.getId());
    }

}