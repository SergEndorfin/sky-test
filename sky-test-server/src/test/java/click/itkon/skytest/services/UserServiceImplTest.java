package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.mappers.UserMapper;
import click.itkon.skytest.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserMapper userMapperMock;

    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    UserServiceImpl userService;

    User userEntity = User.builder().build();
    UserResponseDto userResponseDto = UserResponseDto.builder().build();
    UUID id = UUID.randomUUID();

    @Test
    void createUser() {
        var userCreateRequestDto = UserAuthRequestDto.builder().build();

        when(userMapperMock.createUserDtoToUser(userCreateRequestDto)).thenReturn(userEntity);
        when(userRepositoryMock.save(userEntity)).thenReturn(userEntity);
        doNothing().when(userRepositoryMock).flush();

        userService.createUser(userCreateRequestDto);

        verify(userMapperMock).createUserDtoToUser(userCreateRequestDto);
        verify(userRepositoryMock).save(userEntity);
        verify(userRepositoryMock).flush();
    }

    @Test
    void listUsers() {
        List<User> foundUsers = List.of(userEntity);
        when(userRepositoryMock.findAll()).thenReturn(foundUsers);
        when(userMapperMock.userToResponseDto(userEntity)).thenReturn(userResponseDto);

        userService.listUsers();

        verify(userRepositoryMock).findAll();
        verify(userMapperMock).userToResponseDto(userEntity);
    }

    @Test
    void getUserById() {
        Optional<User> userOptional = Optional.of(userEntity);
        when(userRepositoryMock.findById(id)).thenReturn(userOptional);
        when(userMapperMock.userToResponseDto(userEntity)).thenReturn(userResponseDto);

        userService.getUserById(id);

        verify(userRepositoryMock).findById(id);
        verify(userMapperMock).userToResponseDto(userEntity);
    }

    @Test
    void deleteUser_userFound() {
        Optional<User> userOptional = Optional.of(userEntity);
        when(userRepositoryMock.findById(id)).thenReturn(userOptional);
        doNothing().when(userRepositoryMock).delete(userEntity);

        userService.deleteUser(id);

        verify(userRepositoryMock).findById(id);
        verify(userRepositoryMock).delete(userEntity);
    }

    @Test
    void deleteUser_userNotFound() {
        when(userRepositoryMock.findById(id)).thenReturn(Optional.empty());
        NotFoundException ex = assertThrows(NotFoundException.class, () -> userService.deleteUser(id));
        String expectedErrorMessage = "Source not found. ID: " + id.toString();
        assertEquals(expectedErrorMessage, ex.getMessage());
    }
}
