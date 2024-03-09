package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.dto.PetDTO;
import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.application.mapper.PetMapper;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PetComponent {

    private final PetRepository petRepository;

    @Cacheable(value = "petListCache", key = "'allPets'")
    public List<PetDTO> getPetList() {
        List<PetEntity> petEntityList = petRepository.findAll();
        return PetMapper.INSTANCE.toDtoList(petEntityList);
    }

    @CachePut(value = "petCache", key = "#result.id")
    @CacheEvict(value = "petListCache", allEntries = true)
    public PetDTO savePet(PetDTO petDTO) {
        PetEntity petEntity = PetMapper.INSTANCE.toEntity(petDTO);
        PetEntity savedPet = petRepository.save(petEntity);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @CachePut(value = "petCache", key = "#petDTO.id")
    @CacheEvict(value = "petListCache", allEntries = true)
    public PetDTO updatePet(PetDTO petDTO) {
        PetEntity existingPet = petRepository.findById(petDTO.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        existingPet.setName(petDTO.getName());
        existingPet.setType(petDTO.getType());
        existingPet.setBreed(petDTO.getBreed());

        PetEntity savedPet = petRepository.save(existingPet);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Cacheable(value = "petCache", key = "#id")
    public PetDTO getPetById(Long id) {
        PetEntity petEntity = petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        return PetMapper.INSTANCE.toDto(petEntity);
    }

    @Caching(evict = {
            @CacheEvict(value = "petCache", key = "#id"),
            @CacheEvict(value = "petListCache", allEntries = true)
    })
    public String deletePet(Long id) {
        petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        petRepository.deleteById(id);

        return String.format("Pet with id %d deleted successfully", id);
    }
}
