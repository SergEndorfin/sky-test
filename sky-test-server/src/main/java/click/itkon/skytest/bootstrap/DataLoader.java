package click.itkon.skytest.bootstrap;

import click.itkon.apifirst.model.UserDto;
import click.itkon.apifirst.model.UserNameDto;
import click.itkon.skytest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UserDto userDto = UserDto.builder()
                .email("sam@mail.com")
                .password("123")
                .name(UserNameDto.builder().firstName("Sam").lastName("Smith").build())
                .build();
        userRepository.save(userDto);
    }
}
