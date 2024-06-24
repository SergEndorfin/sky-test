package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserNameDto;
import click.itkon.skytest.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService customerService;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Test
    void createUser() {
//        UserDto userDto = createUserDto();
//        UserDto createdUser = customerService.createUser(userDto);

//        assertNotNull(createdUser);
//        assertNotNull(createdUser.getId());
//
//        User user = userRepository.findById(createdUser.getId()).orElseThrow();
//        assertNotNull(user);
//        UserName userName = user.getName();
//        assertNotNull(userName);
//        assertEquals(userDto.getName().getFirstName(), userName.getFirstName());
//        assertEquals(userDto.getName().getLastName(), userName.getLastName());
    }

    @Test
    void listUsers() {
    }

    @Test
    void getUserById() {
    }

//    UserDto createUserDto() {
//        return UserDto.builder()
//                .email("email@email.com")
//                .password("qwe123")
//                .name(UserNameDto.builder().firstName("Sam").lastName("Samson").build())
//                .role(UserDto.RoleEnum.USER)
//                .build();
//    }
}
