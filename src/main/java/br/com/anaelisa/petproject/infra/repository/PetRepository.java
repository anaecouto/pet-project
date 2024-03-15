package br.com.anaelisa.petproject.infra.repository;

import br.com.anaelisa.petproject.domain.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findAllByOwnerId(Long userId);
}
