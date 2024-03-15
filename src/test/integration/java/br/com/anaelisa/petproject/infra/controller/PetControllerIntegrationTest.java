package br.com.anaelisa.petproject.infra.controller;

import br.com.anaelisa.petproject.BaseIntegrationTest;
import br.com.anaelisa.petproject.application.component.pet.dto.PetDTO;
import br.com.anaelisa.petproject.domain.entity.PetEntity;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PetControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void getAllPetsSuccess() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/pet");

        mvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", equalTo(1)))
                .andExpect(jsonPath("$.content[0].name", equalTo("name")))
                .andExpect(jsonPath("$.content[0].breed", equalTo("breed")))
                .andExpect(jsonPath("$.content[0].type", equalTo("type")))
                .andExpect(jsonPath("$.content[1].id", equalTo(2)))
                .andExpect(jsonPath("$.content[1].name", equalTo("name2")))
                .andExpect(jsonPath("$.content[1].breed", equalTo("breed2")))
                .andExpect(jsonPath("$.content[1].type", equalTo("type2")));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void savePetEntity() throws Exception {
        PetDTO petDTO = PetDTO.builder().name("name").breed("breed").type("type").build();

        String content = (new GsonBuilder().setPrettyPrinting().create().toJson(petDTO));

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/pet")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content.id", is(4)));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void savePetEntityFailWithNullFields() throws Exception {
        PetEntity petEntity = new PetEntity();

        petEntity.setName("name");

        String content = (new GsonBuilder().setPrettyPrinting().create().toJson(petEntity));

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/pet")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.content", equalTo(null)));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void getPetByIdSuccess() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/pet/{id}", 1L);

        mvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content.id", equalTo(1)))
                .andExpect(jsonPath("$.content.name", equalTo("name")))
                .andExpect(jsonPath("$.content.breed", equalTo("breed")))
                .andExpect(jsonPath("$.content.type", equalTo("type")));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void deletePetById() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/pet/{id}", 1L);

        mvc.perform(request)
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.content", equalTo("Pet with id " + 1 + " deleted successfully")));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void deletePetByIdFailureWithNoExistentId() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/pet/{id}", 4L);

        mvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.content", equalTo(null)))
                .andExpect(jsonPath("$.error", equalTo("Pet not found")));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void getPetByIdFailureIdNotExists() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/pet/{id}", 3L);
        mvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.content", equalTo(null)))
                .andExpect(jsonPath("$.status", equalTo(404)))
                .andExpect(jsonPath("$.error", equalTo("Pet not found")));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void updatePet() throws Exception {
        PetEntity petEntity = new PetEntity();

        petEntity.setId(1L);
        petEntity.setName("nameUpdated");
        petEntity.setType("typeUpdated");
        petEntity.setBreed("breed");

        String content = (new GsonBuilder().setPrettyPrinting().create().toJson(petEntity));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mvc.perform(request)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.content.id", equalTo(1)))
                .andExpect(jsonPath("$.content.name", equalTo("nameUpdated")))
                .andExpect(jsonPath("$.content.breed", equalTo("breed")))
                .andExpect(jsonPath("$.content.type", equalTo("typeUpdated")));
    }

    @Test
    @Sql("/scripts/insert-into-pet-table.sql")
    @WithMockUser("anacouto")
    void updatePetFailureWithNonExistentId() throws Exception {
        PetEntity petEntity = new PetEntity();

        petEntity.setId(4L);
        petEntity.setName("nameUpdated");
        petEntity.setType("typeUpdated");
        petEntity.setBreed("breedUpdated");

        String content = (new GsonBuilder().setPrettyPrinting().create().toJson(petEntity));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.content", equalTo(null)))
                .andExpect(jsonPath("$.status", equalTo(404)))
                .andExpect(jsonPath("$.error", equalTo("Pet not found")));
    }
}
