package br.com.anaelisa.petproject.application.component.pet.service;

import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.application.component.customer.service.AuthenticatedUserService;
import br.com.anaelisa.petproject.application.error.PetNotFoundException;
import br.com.anaelisa.petproject.application.component.pet.mapper.PetMapper;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
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
        CustomerEntity customerEntity = authenticatedUserService.getLoggedUser();

        List<PetEntity> petEntityList = petRepository.findAllByOwnerId(customerEntity.getId());
        return PetMapper.INSTANCE.toDtoList(petEntityList);
    }

    @Transactional
    public PetDTO savePet(PetDTO petDTO) {
        CustomerEntity customerEntity = authenticatedUserService.getLoggedUser();

        PetEntity petEntity = PetMapper.INSTANCE.toEntity(petDTO);
        petEntity.setOwner(customerEntity);

        PetEntity savedPet = petRepository.save(petEntity);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Transactional
    public PetDTO updatePet(PetDTO petDTO) {
        CustomerEntity customerEntity = authenticatedUserService.getLoggedUser();

        PetEntity existingPet = petRepository.findByIdAndOwnerId(petDTO.getId(), customerEntity.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        existingPet.setName(petDTO.getName());
        existingPet.setType(petDTO.getType());
        existingPet.setBreed(petDTO.getBreed());

        PetEntity savedPet = petRepository.save(existingPet);
        return PetMapper.INSTANCE.toDto(savedPet);
    }

    @Transactional(readOnly = true)
    public PetDTO getPetById(Long id) {
        CustomerEntity customerEntity = authenticatedUserService.getLoggedUser();

        PetEntity petEntity = petRepository.findByIdAndOwnerId(id, customerEntity.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        return PetMapper.INSTANCE.toDto(petEntity);
    }

    @Transactional
    public String deletePet(Long id) {
        CustomerEntity customerEntity = authenticatedUserService.getLoggedUser();

        petRepository.findByIdAndOwnerId(id, customerEntity.getId()).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        petRepository.deleteById(id);

        return String.format("Pet with id %d deleted successfully", id);
    }
}
