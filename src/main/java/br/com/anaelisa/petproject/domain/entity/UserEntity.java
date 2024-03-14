package br.com.anaelisa.petproject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;

}
