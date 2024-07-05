package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.apifirst.model.UserUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserAuthRequestDto userAuthRequestDto);

    List<UserResponseDto> listUsers();

    UserResponseDto getUserById(UUID userId);

    void deleteUser(UUID userId);

    UserResponseDto updateUser(UUID userId, UserUpdateRequestDto updateRequestDto);
}
