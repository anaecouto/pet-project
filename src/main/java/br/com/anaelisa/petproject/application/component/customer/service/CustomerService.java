package br.com.anaelisa.petproject.application.component.customer.service;

import br.com.anaelisa.petproject.application.component.customer.dto.CustomerDTO;
import br.com.anaelisa.petproject.application.component.customer.mapper.CustomerMapper;
import br.com.anaelisa.petproject.domain.entity.CustomerEntity;
import br.com.anaelisa.petproject.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public List<CustomerDTO> listAllCustomers() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();

        return CustomerMapper.INSTANCE.toDtoList(customerEntityList);
    }

}
