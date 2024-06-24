package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserCreateRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.mappers.UserMapper;
import click.itkon.skytest.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        log.info("Creating user");
        User savedUser = userRepository.save(userMapper.createUserDtoToUser(userCreateRequestDto));
        userRepository.flush();
        log.info("User created. Id: {}", savedUser.getId());
        return userMapper.userToResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> listUsers() {
        log.info("Listing users");
        return userRepository.findAll().stream()
                .map(userMapper::userToResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(UUID userId) {
        log.info("Getting user by id: {}", userId);
        return userRepository.findById(userId)
                .map(userMapper::userToResponseDto)
                .orElseThrow();
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new NotFoundException("User Not Found");
                });
    }
}
