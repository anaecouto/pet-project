package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetComponentTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetComponent petComponent;

    @Test
    void shouldListAllPets() {
        List<PetEntity> petEntityList = List.of(
                new PetEntity(1L, "pet 1", "type 1", "breed 1"),
                new PetEntity(2L, "pet 2", "type 2", "breed 2"));

        when(petRepository.findAll()).thenReturn(petEntityList);

        List<PetEntity> petEntityListResult = petComponent.getPetList();

        assertEquals(Long.valueOf(1L), petEntityListResult.get(0).getId());
        assertEquals( "pet 1", petEntityListResult.get(0).getName());
        assertEquals("type 1", petEntityListResult.get(0).getType());
        assertEquals("breed 1", petEntityListResult.get(0).getBreed());
    }

    @Test
    void shouldSavePet() {
        PetEntity petEntity = new PetEntity();
        petEntity.setId(1L);

        when(petRepository.save(eq(petEntity))).thenReturn(petEntity);

        PetEntity pet = petComponent.savePet(petEntity);

        assertEquals(Long.valueOf(1L), pet.getId());
    }

    @Test
    void shouldThrowErrorIfPetNotExists() {

        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        PetNotFoundException exception = assertThrows(PetNotFoundException.class, () -> petComponent.getPetById(1L));

        assertEquals("Pet not found", exception.getMessage());
    }

    @Test
    void shouldFindPet() {

        PetEntity petEntity = new PetEntity();
        petEntity.setId(1L);

        when(petRepository.findById(1L)).thenReturn(Optional.of(petEntity));

        PetEntity petEntityResult = petComponent.getPetById(1L);

        assertEquals(Long.valueOf(1L), petEntityResult.getId());
    }

}