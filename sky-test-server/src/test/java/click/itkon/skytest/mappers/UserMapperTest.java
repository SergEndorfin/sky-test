package click.itkon.skytest.mappers;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserNameDto;
import click.itkon.apifirst.model.UserUpdateRequestDto;
import click.itkon.skytest.domain.ExternalProject;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.domain.UserName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    UserMapper userMapper = new UserMapperImpl_();

    @Test
    void createUserDtoToUser() {
        var userCreateRequestDto = UserAuthRequestDto.builder()
                .password("qwe123")
                .email("qwe@gmail.com")
                .build();

        var userEntity = userMapper.createUserDtoToUser(userCreateRequestDto);

        assertEquals(userCreateRequestDto.getEmail(), userEntity.getEmail());
        assertEquals(userCreateRequestDto.getPassword(), userEntity.getPassword());
        assertNull(userEntity.getName());
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

        var userResponseDto = userMapper.userToResponseDto(userEntity);

        assertEquals(userEntity.getId(), userResponseDto.getId());
        assertEquals(userEntity.getName().getFirstName(), userResponseDto.getName().getFirstName());
        assertEquals(userEntity.getName().getLastName(), userResponseDto.getName().getLastName());
        assertEquals(userEntity.getExternalProjects().size(), userResponseDto.getExternalProjects().size());
        assertEquals(userEntity.getDateCreated(), userResponseDto.getDateCreated());
        assertEquals(userEntity.getDateUpdated(), userResponseDto.getDateUpdated());
    }

    @Test
    void updateUserDtoToUser_withoutName() {
        var updateRequestDto = UserUpdateRequestDto.builder()
                .id(UUID.randomUUID())
                .email("test@mail.com")
                .password("123123")
                .build();
        var user = userMapper.updateUserDtoToUser(updateRequestDto);

        assertEquals(updateRequestDto.getId(), user.getId());
        assertEquals(updateRequestDto.getEmail(), user.getEmail());
        assertEquals(updateRequestDto.getPassword(), user.getPassword());
        assertNull(user.getName());
        assertEquals(0, user.getExternalProjects().size());
    }

    @Test
    void updateUserDtoToUser_withName() {
        var updateRequestDto = UserUpdateRequestDto.builder()
                .id(UUID.randomUUID())
                .email("test@mail.com")
                .password("123123")
                .name(UserNameDto.builder().prefix("Mr.").firstName("Sam").lastName("Samson").build())
                .build();
        var user = userMapper.updateUserDtoToUser(updateRequestDto);

        assertEquals(updateRequestDto.getId(), user.getId());
        assertEquals(updateRequestDto.getEmail(), user.getEmail());
        assertEquals(updateRequestDto.getPassword(), user.getPassword());
        var userName = user.getName();
        assertNotNull(userName);
        assertEquals(updateRequestDto.getName().getPrefix(), userName.getPrefix());
        assertEquals(updateRequestDto.getName().getFirstName(), userName.getFirstName());
        assertEquals(updateRequestDto.getName().getLastName(), userName.getLastName());

        assertEquals(0, user.getExternalProjects().size());
    }
}
