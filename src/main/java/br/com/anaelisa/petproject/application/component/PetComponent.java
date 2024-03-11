package br.com.anaelisa.petproject.application.component;

import br.com.anaelisa.petproject.application.dto.PetDTO;
import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.application.mapper.PetMapper;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PetComponent {

    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public List<PetDTO> getPetList() {
        List<PetEntity> petEntityList = petRepository.findAll();
        return PetMapper.INSTANCE.toDtoList(petEntityList);
    }

    @Transactional
    public PetDTO savePet(PetDTO petDTO) {
        PetEntity petEntity = PetMapper.INSTANCE.toEntity(petDTO);
        PetEntity savedPet = petRepository.save(petEntity);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Transactional
    public PetDTO updatePet(PetDTO petDTO) {
        PetEntity existingPet = petRepository.findById(petDTO.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        existingPet.setName(petDTO.getName());
        existingPet.setType(petDTO.getType());
        existingPet.setBreed(petDTO.getBreed());

        PetEntity savedPet = petRepository.save(existingPet);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Transactional(readOnly = true)
    public PetDTO getPetById(Long id) {
        PetEntity petEntity = petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        return PetMapper.INSTANCE.toDto(petEntity);
    }

    @Transactional
    public String deletePet(Long id) {
        petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        petRepository.deleteById(id);

        return String.format("Pet with id %d deleted successfully", id);
    }
}
