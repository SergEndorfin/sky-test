package click.itkon.skytest.mappers;

import click.itkon.apifirst.model.UserCreateRequestDto;
import click.itkon.apifirst.model.UserNameDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.domain.ExternalProject;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.domain.UserName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    UserMapper userMapper = new UserMapperImpl();

    @Test
    void createUserDtoToUser() {
        var userCreateRequestDto = UserCreateRequestDto.builder()
                .password("qwe123")
                .email("qwe@gmail.com")
                .name(UserNameDto.builder().firstName("Sam").lastName("Samson").build())
                .build();
        User userEntity = userMapper.createUserDtoToUser(userCreateRequestDto);

        assertEquals(userCreateRequestDto.getEmail(), userEntity.getEmail());
        assertEquals(userCreateRequestDto.getPassword(), userEntity.getPassword());
        assertEquals(userCreateRequestDto.getName().getFirstName(), userEntity.getName().getFirstName());
        assertEquals(userCreateRequestDto.getName().getLastName(), userEntity.getName().getLastName());
    }

    @Test
    void userToResponseDto() {
        var userEntity = User.builder()
                .id(UUID.randomUUID())
                .password("qwe123")
                .email("qwe@gmail.com")
                .name(UserName.builder().firstName("Sam").lastName("Samson").build())
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now())
                .externalProjects(Collections.singletonList(ExternalProject.builder().build()))
                .build();

        UserResponseDto userResponseDto = userMapper.userToResponseDto(userEntity);

        assertEquals(userEntity.getId(), userResponseDto.getId());
        assertEquals(userEntity.getName().getFirstName(), userResponseDto.getName().getFirstName());
        assertEquals(userEntity.getName().getLastName(), userResponseDto.getName().getLastName());
        assertEquals(userEntity.getExternalProjects().size(), userResponseDto.getExternalProjects().size());
        assertEquals(userEntity.getDateCreated(), userResponseDto.getDateCreated());
        assertEquals(userEntity.getDateUpdated(), userResponseDto.getDateUpdated());
    }
}
