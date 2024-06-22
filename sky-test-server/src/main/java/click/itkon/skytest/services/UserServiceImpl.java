package click.itkon.skytest.services;

import click.itkon.apifirst.model.User;
import click.itkon.skytest.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> listUsers() {
        log.info("Retrieve -> all users");
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
