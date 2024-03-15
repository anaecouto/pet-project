package br.com.anaelisa.petproject.application.component.pet.service;

import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.application.component.user.service.AuthenticatedUserService;
import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.application.component.pet.mapper.PetMapper;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.domain.entity.UserEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import br.com.anaelisa.petproject.infra.security.implementation.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @Transactional(readOnly = true)
    public List<PetDTO> getPetList() {
        UserEntity userEntity = authenticatedUserService.getLoggedUser();

        List<PetEntity> petEntityList = petRepository.findAllByOwnerId(userEntity.getId());
        return PetMapper.INSTANCE.toDtoList(petEntityList);
    }

    @Transactional
    public PetDTO savePet(PetDTO petDTO) {
        UserEntity userEntity = authenticatedUserService.getLoggedUser();

        PetEntity petEntity = PetMapper.INSTANCE.toEntity(petDTO);
        petEntity.setOwner(userEntity);

        PetEntity savedPet = petRepository.save(petEntity);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Transactional
    public PetDTO updatePet(PetDTO petDTO) {
        UserEntity userEntity = authenticatedUserService.getLoggedUser();

        PetEntity existingPet = petRepository.findByIdAndOwnerId(petDTO.getId(), userEntity.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        existingPet.setName(petDTO.getName());
        existingPet.setType(petDTO.getType());
        existingPet.setBreed(petDTO.getBreed());

        PetEntity savedPet = petRepository.save(existingPet);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Transactional(readOnly = true)
    public PetDTO getPetById(Long id) {
        UserEntity userEntity = authenticatedUserService.getLoggedUser();

        PetEntity petEntity = petRepository.findByIdAndOwnerId(id, userEntity.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        return PetMapper.INSTANCE.toDto(petEntity);
    }

    @Transactional
    public String deletePet(Long id) {
        UserEntity userEntity = authenticatedUserService.getLoggedUser();

        petRepository.findByIdAndOwnerId(id, userEntity.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        petRepository.deleteById(id);

        return String.format("Pet with id %d deleted successfully", id);
    }
}
