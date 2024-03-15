package br.com.anaelisa.petproject.application.component.user.mapper;

import br.com.anaelisa.petproject.application.component.user.dto.UserDTO;
import br.com.anaelisa.petproject.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "petList", target = "petList")
    UserDTO toDto(UserEntity userEntity);

    @Mapping(source = "petList", target = "petList")
    UserEntity toEntity(UserDTO userDTO);

    List<UserDTO> toDtoList(List<UserEntity> userEntityList);

    List<UserEntity> toEntityList(List<UserDTO> userDTOList);
}
