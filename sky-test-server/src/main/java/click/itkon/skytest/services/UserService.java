package click.itkon.skytest.services;

import click.itkon.apifirst.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> listUsers();

    User getUserById(UUID userId);
}
