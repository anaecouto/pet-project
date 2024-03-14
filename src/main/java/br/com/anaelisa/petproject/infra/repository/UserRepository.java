package br.com.anaelisa.petproject.infra.repository;

import br.com.anaelisa.petproject.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
