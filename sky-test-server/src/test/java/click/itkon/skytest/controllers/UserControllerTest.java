package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.UserCreateRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void createUser_ShouldReturn201() throws Exception {
        var userCreateRequestDto = UserCreateRequestDto.builder().build();
        var userResponseDto = UserResponseDto.builder().build();

        when(userService.createUser(any(UserCreateRequestDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(post(UserController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateRequestDto)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).createUser(any(UserCreateRequestDto.class));
    }
}
