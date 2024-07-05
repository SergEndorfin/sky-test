package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.skytest.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        var createUserRequestDto = createUserAUthRequestDto();
        var createdUser = userService.createUser(createUserRequestDto);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());

        var user = userRepository.findById(createdUser.getId()).orElseThrow();

        assertNotNull(user);
        assertNull(user.getName());
    }

    UserAuthRequestDto createUserAUthRequestDto() {
        return UserAuthRequestDto.builder()
                .email("email@email.com")
                .password("qwe123")
                .build();
    }
}
