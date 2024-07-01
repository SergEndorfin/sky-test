package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserCreateRequestDto;
import click.itkon.apifirst.model.UserNameDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.domain.UserName;
import click.itkon.skytest.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
class UserServiceImplIT {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Test
    void createUser() {
        UserCreateRequestDto createRequestDto = createUserDto();
        UserResponseDto createdUser = userService.createUser(createRequestDto);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());

        User user = userRepository.findById(createdUser.getId()).orElseThrow();
        assertNotNull(user);
        UserName userName = user.getName();
        assertNotNull(userName);
        assertEquals(createRequestDto.getName().getFirstName(), userName.getFirstName());
        assertEquals(createRequestDto.getName().getLastName(), userName.getLastName());
    }

    UserCreateRequestDto createUserDto() {
        return UserCreateRequestDto.builder()
                .email("email@email.com")
                .password("qwe123")
                .name(UserNameDto.builder().firstName("Sam").lastName("Samson").build())
                .build();
    }
}
