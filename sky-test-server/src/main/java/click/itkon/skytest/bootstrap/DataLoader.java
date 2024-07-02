package click.itkon.skytest.bootstrap;

import click.itkon.skytest.domain.ExternalProject;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.domain.UserName;
import click.itkon.skytest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!prod")
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        String email = "sam@mail.com";

        if (!userRepository.existsByEmail(email)) {
            User user = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode("123qwe"))
                    .name(UserName.builder().prefix("Mr.").firstName("Sam").lastName("Smith").build())
                    .externalProjects(List.of(
                            ExternalProject.builder()
                                    .name("Project 1")
                                    .description("First important project").build(),
                            ExternalProject.builder()
                                    .name("Project 2")
                                    .description("Second important project").build()))
                    .build();

            user.getExternalProjects().forEach(ep -> ep.setUser(user));

            userRepository.save(user);
        }
    }
}
