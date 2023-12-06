package br.com.anaelisa.petproject.domain.component;

import br.com.anaelisa.petproject.domain.entity.PetEntity;
import br.com.anaelisa.petproject.errors.PetNotFoundException;
import br.com.anaelisa.petproject.infra.dtos.UpdatePetDTO;
import br.com.anaelisa.petproject.infra.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetComponent {

    @Autowired
    PetRepository petRepository;

    public List<PetEntity> getPetList() {
        return petRepository.findAll();
    }

    public PetEntity savePet(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public PetEntity updatePet(UpdatePetDTO updatePetDTO, PetEntity existingPet) {
        updatePetDTO.getName().ifPresent(existingPet::setName);
        updatePetDTO.getType().ifPresent(existingPet::setType);
        updatePetDTO.getBreed().ifPresent(existingPet::setBreed);

        return petRepository.save(existingPet);
    }

    public PetEntity getPetById(Long id) {
        PetEntity pet = petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        return pet;
    }

    public String deletePet(Long id) {
        petRepository.findById(id).orElseThrow(() ->
                new PetNotFoundException("Pet not found"));

        petRepository.deleteById(id);

        return "Pet with id " + id + " deleted successfully";
    }
}
