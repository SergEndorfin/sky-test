package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> listUsers();

    UserDto getUserById(UUID userId);
}
