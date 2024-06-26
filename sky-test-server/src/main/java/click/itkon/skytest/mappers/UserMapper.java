package click.itkon.skytest.mappers;

import click.itkon.apifirst.model.UserCreateRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    User createUserDtoToUser(UserCreateRequestDto userCreateRequestDto);

    UserResponseDto userToResponseDto(User user);
}
