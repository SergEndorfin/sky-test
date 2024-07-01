package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserCreateRequestDto;
import click.itkon.apifirst.model.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserCreateRequestDto userCreateRequestDto);

    List<UserResponseDto> listUsers();

    UserResponseDto getUserById(UUID userId);

    void deleteUser(UUID userId);
}
