package click.itkon.skytest.services;

import click.itkon.apifirst.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    public List<User> listUsers() {
        log.info("Retrieve -> all users");
        return List.of(User.builder()
                .email("mail@mail.com")
                .password("secret")
                .build());
    }
}
