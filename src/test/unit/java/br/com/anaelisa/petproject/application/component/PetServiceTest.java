//package br.com.anaelisa.petproject.application.component;
//
//import br.com.anaelisa.petproject.application.component.pet.service.PetService;
//import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
//import br.com.anaelisa.petproject.application.error.PetNotFoundException;
//import br.com.anaelisa.petproject.domain.entity.PetEntity;
//import br.com.anaelisa.petproject.infra.repository.PetRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PetServiceTest {
//
//    @Mock
//    PetRepository petRepository;
//
//    @InjectMocks
//    PetService petService;
//
//    @Test
//    void shouldListAllPets() {
//        List<PetEntity> petEntityList = List.of(
//                new PetEntity(1L, "pet 1", "type 1", "breed 1"),
//                new PetEntity(2L, "pet 2", "type 2", "breed 2"));
//
//        when(petRepository.findAll()).thenReturn(petEntityList);
//
//        List<PetDTO> petEntityListResult = petService.getPetList();
//
//        Assertions.assertEquals(Long.valueOf(1L), petEntityListResult.get(0).getId());
//        Assertions.assertEquals("pet 1", petEntityListResult.get(0).getName());
//        Assertions.assertEquals("type 1", petEntityListResult.get(0).getType());
//        Assertions.assertEquals("breed 1", petEntityListResult.get(0).getBreed());
//    }
//
//    @Test
//    void shouldUpdatePet() {
//
//        PetDTO updatedPetDTO = PetDTO.builder()
//                .id(1L)
//                .name("New name")
//                .type("Dog")
//                .breed("Golden Retriever")
//                .build();
//
//        PetEntity existingPetEntity = new PetEntity();
//        existingPetEntity.setId(1L);
//        existingPetEntity.setName("Old name");
//        existingPetEntity.setType("Dog");
//        existingPetEntity.setBreed("Golden Retriever");
//
//        PetEntity savedPetEntity = new PetEntity();
//        savedPetEntity.setId(1L);
//        savedPetEntity.setName("New name");
//        savedPetEntity.setType("Dog");
//        savedPetEntity.setBreed("Golden Retriever");
//
//        when(petRepository.findById(1L)).thenReturn(Optional.of(existingPetEntity));
//        when(petRepository.save(existingPetEntity)).thenReturn(savedPetEntity);
//
//        PetDTO returnedPetDTO = petService.updatePet(updatedPetDTO);
//
//        Assertions.assertEquals(returnedPetDTO.getId(), updatedPetDTO.getId());
//        Assertions.assertEquals(returnedPetDTO.getName(), updatedPetDTO.getName());
//        Assertions.assertEquals(returnedPetDTO.getType(), updatedPetDTO.getType());
//        Assertions.assertEquals(returnedPetDTO.getBreed(), updatedPetDTO.getBreed());
//        verify(petRepository, times(1)).findById(1L);
//        verify(petRepository, times(1)).save(existingPetEntity);
//    }
//
//    @Test
//    void shouldSavePet() {
//        PetEntity petEntity = new PetEntity();
//        petEntity.setId(1L);
//        petEntity.setName("name");
//
//        PetDTO petDTO = PetDTO.builder()
//                .name("name")
//                .build();
//
//        when(petRepository.save(any(PetEntity.class))).thenReturn(petEntity);
//
//        PetDTO pet = petService.savePet(petDTO);
//
//        Assertions.assertEquals(Long.valueOf(1L), pet.getId());
//        Assertions.assertEquals("name", pet.getName());
//    }
//
//    @Test
//    void shouldThrowErrorIfPetNotExists() {
//
//        when(petRepository.findById(1L)).thenReturn(Optional.empty());
//
//        PetNotFoundException exception = assertThrows(PetNotFoundException.class, () -> petService.getPetById(1L));
//
//        Assertions.assertEquals("Pet not found", exception.getMessage());
//    }
//
//    @Test
//    void shouldFindPet() {
//
//        PetEntity petEntity = new PetEntity();
//        petEntity.setId(1L);
//
//        when(petRepository.findById(1L)).thenReturn(Optional.of(petEntity));
//
//        PetDTO petDTO = petService.getPetById(1L);
//
//        Assertions.assertEquals(Long.valueOf(1L), petDTO.getId());
//    }
//
//}