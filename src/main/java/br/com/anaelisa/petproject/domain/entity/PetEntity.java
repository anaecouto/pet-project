package br.com.anaelisa.petproject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pet_id_seq")
    @SequenceGenerator(name = "pet_id_seq", sequenceName = "pet_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String type;

    @NotNull
    private String breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private CustomerEntity owner;
}
