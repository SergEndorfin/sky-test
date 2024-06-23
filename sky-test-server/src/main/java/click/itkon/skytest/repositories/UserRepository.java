package click.itkon.skytest.repositories;

import click.itkon.apifirst.model.UserDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserDto, UUID> {
}
