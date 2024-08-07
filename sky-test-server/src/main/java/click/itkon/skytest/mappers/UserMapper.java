package click.itkon.skytest.mappers;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.apifirst.model.UserUpdateRequestDto;
import click.itkon.skytest.domain.User;
import org.mapstruct.*;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "externalProjects", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User createUserDtoToUser(UserAuthRequestDto userCreateRequestDto);

    UserResponseDto userToResponseDto(User user);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "externalProjects", ignore = true)
    User updateUserDtoToUser(UserUpdateRequestDto updateRequestDto);
}
