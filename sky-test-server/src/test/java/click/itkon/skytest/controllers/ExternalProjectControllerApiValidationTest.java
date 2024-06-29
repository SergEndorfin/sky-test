package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.ExternalProjectsCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ExternalProjectControllerApiValidationTest extends BaseTest {

    @DisplayName("Add a new External Project")
    @Test
    void addExternalProject() throws Exception {
        ExternalProjectsCreateRequestDto requestDto = getExternalProjectsCreateRequestDto();

        mockMvc.perform(
                        post(ExternalProjectsController.BASE_URL, testUserEntity.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @DisplayName("List users External Project")
    @Test
    void listUsersExternalProjects() throws Exception {
        mockMvc.perform(get(ExternalProjectsController.BASE_URL, testUserEntity.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    private ExternalProjectsCreateRequestDto getExternalProjectsCreateRequestDto() {
        return ExternalProjectsCreateRequestDto.builder()
                .name("Next Project")
                .description("Next Project")
                .build();
    }
}
