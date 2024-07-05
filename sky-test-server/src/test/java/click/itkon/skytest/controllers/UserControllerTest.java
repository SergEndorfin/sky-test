package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.config.NoSecurityConfig;
import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(NoSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userServiceMock;

    @Test
    public void createUser_ShouldReturn201() throws Exception {
        var userCreateRequestDto = UserAuthRequestDto.builder().build();
        var userResponseDto = UserResponseDto.builder().build();

        when(userServiceMock.createUser(any(UserAuthRequestDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(post(UserController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        verify(userServiceMock, times(1)).createUser(any(UserAuthRequestDto.class));
    }

    @Test
    public void createUser_ShouldReturn409() throws Exception {
        var userCreateRequestDto = UserAuthRequestDto.builder().build();

        doThrow(DataIntegrityViolationException.class).when(userServiceMock).createUser(any(UserAuthRequestDto.class));

        mockMvc.perform(post(UserController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isConflict());

        verify(userServiceMock, times(1)).createUser(any(UserAuthRequestDto.class));
    }

    @Test
    public void getAllUsers_ShouldReturn200() throws Exception {
        when(userServiceMock.listUsers()).thenReturn(List.of(UserResponseDto.builder().build()));

        mockMvc.perform(get(UserController.BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    public void getUser_ShouldReturn200() throws Exception {
        var userId = UUID.randomUUID();
        when(userServiceMock.getUserById(userId)).thenReturn(UserResponseDto.builder().id(userId).build());
        mockMvc.perform(get(UserController.BASE_URL + "/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }

    @Test
    public void deleteUser_ShouldReturn204() throws Exception {
        var userId = UUID.randomUUID();
        when(userServiceMock.getUserById(userId)).thenReturn(UserResponseDto.builder().id(userId).build());
        mockMvc.perform(delete(UserController.BASE_URL + "/{userId}", userId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteUser_ShouldReturn404() throws Exception {
        var userId = UUID.randomUUID();
        doThrow(NotFoundException.class).when(userServiceMock).deleteUser(userId);
        mockMvc.perform(delete(UserController.BASE_URL + "/{userId}", userId))
                .andExpect(status().isNotFound());
    }
}
