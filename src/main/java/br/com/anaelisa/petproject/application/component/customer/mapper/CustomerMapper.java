package br.com.anaelisa.petproject.application.component.customer.mapper;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "petList", target = "petList")
    CustomerDTO toDto(CustomerEntity customerEntity);

    @Mapping(source = "petList", target = "petList")
    CustomerEntity toEntity(CustomerDTO customerDTO);

    List<CustomerDTO> toDtoList(List<CustomerEntity> customerEntityList);

    List<CustomerEntity> toEntityList(List<CustomerDTO> customerDTOList);
}
