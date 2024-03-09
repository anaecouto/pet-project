package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.dto.UpdatePetDTO;
import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PetComponent {

    private final PetRepository petRepository;

    @Cacheable(value = "petListCache", key = "'allPets'")
    public List<PetEntity> getPetList() {
        return petRepository.findAll();
    }

    @CachePut(value = "petCache", key = "#result.id")
    public PetEntity savePet(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    @CachePut(value = "petCache", key = "#id")
    public PetEntity updatePet(UpdatePetDTO updatePetDTO, Long id) {
        PetEntity existingPet = getPetById(id);

        updatePetDTO.getName().ifPresent(existingPet::setName);
        updatePetDTO.getType().ifPresent(existingPet::setType);
        updatePetDTO.getBreed().ifPresent(existingPet::setBreed);

        return petRepository.save(existingPet);
    }

    @Cacheable(value = "petCache", key = "#id")
    public PetEntity getPetById(Long id) {
        PetEntity pet = petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        return pet;
    }

    @CacheEvict(value = "petCache", key = "#id")
    public String deletePet(Long id) {
        petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        petRepository.deleteById(id);

        return "Pet with id " + id + " deleted successfully";
    }
}
