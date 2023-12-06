package br.com.anaelisa.petproject.infra.dtos;

import jakarta.annotation.Nullable;

import java.util.Optional;

public class UpdatePetDTO {

    @Nullable
    private Optional<String> name = Optional.empty();

    @Nullable
    private Optional<String> type = Optional.empty();

    @Nullable
    private Optional<String> breed = Optional.empty();

    @Nullable
    public Optional<String> getName() {
        return name;
    }

    public void setName(@Nullable Optional<String> name) {
        this.name = name;
    }

    @Nullable
    public Optional<String> getType() {
        return type;
    }

    public void setType(@Nullable Optional<String> type) {
        this.type = type;
    }

    @Nullable
    public Optional<String> getBreed() {
        return breed;
    }

    public void setBreed(@Nullable Optional<String> breed) {
        this.breed = breed;
    }
}
