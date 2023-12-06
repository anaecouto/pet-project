package br.com.anaelisa.petproject.domain;

import br.com.anaelisa.petproject.domain.entity.PetEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class PetEntityTest {

    @Test
    public void setIdAndGetId() {
        PetEntity petEntity = new PetEntity();

        petEntity.setId(1L);

        assertEquals(1L, (long) petEntity.getId());
    }

    @Test
    public void getIdWithoutSetting() {
        PetEntity petEntity = new PetEntity();

        assertNull(petEntity.getId());
    }
}
