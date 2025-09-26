package br.com.anaelisa.petproject.application.component.pet.mapper;

import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetDTO toDto(PetEntity petEntity);

    PetEntity toEntity(PetDTO petDTO);

    List<PetDTO> toDtoList(List<PetEntity> petEntity);
}
