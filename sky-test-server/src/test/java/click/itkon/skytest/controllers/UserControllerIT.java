package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.UserAuthRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UserControllerIT extends BaseTest {

    @DisplayName("Create a new User")
    @Test
    void createUser() throws Exception {
        var userCreateRequestDto = UserAuthRequestDto.builder()
                .email("test@email.com")
                .password("qwe123")
                .build();

        mockMvc.perform(post(UserController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @DisplayName("Get Users list")
    @Test
    void listUsers() throws Exception {
        mockMvc.perform(get(UserController.BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @DisplayName("Get by Id")
    @Test
    void getUserById_existingUser() throws Exception {
        mockMvc.perform(get(UserController.BASE_URL + "/{userId}", testUserEntity.getId())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUserEntity.getId().toString()));
    }

    @DisplayName("Get by Id")
    @Test
    void getUserById_userNotFoun() throws Exception {
        mockMvc.perform(get(UserController.BASE_URL + "/{userId}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Transactional
    @DisplayName("Delete user by Id when found")
    @Test
    void deleteUserById_existingUser() throws Exception {
        mockMvc.perform(delete(UserController.BASE_URL + "/{userId}", testUserEntity.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @DisplayName("Delete user by Id when not found")
    @Test
    void deleteUserById_userNotFound() throws Exception {
        mockMvc.perform(delete(UserController.BASE_URL + "/{userId}", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
