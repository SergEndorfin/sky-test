package click.itkon.skytest.services;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.apifirst.model.UserUpdateRequestDto;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.mappers.UserMapper;
import click.itkon.skytest.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public UserResponseDto createUser(UserAuthRequestDto userCreateRequestDto) {
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
                .orElseThrow(() -> new NotFoundException(userId.toString()));
    }

    @Override
    public void deleteUser(UUID userId) {
        log.info("Deleting user by id: {}", userId);
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new NotFoundException(userId.toString());
                });
    }

    @Override
    public UserResponseDto updateUser(UUID userId, UserUpdateRequestDto updateRequestDto) {
        log.info("Updating user");
        var updatedData = userRepository
                .findById(userId)
                .map(user -> {
                    var updates = userMapper.updateUserDtoToUser(updateRequestDto);
                    updates.setId(userId);
                    updates.setDateCreated(user.getDateCreated());
                    updates.setExternalProjects(user.getExternalProjects());
                    return updates;
                }).orElseThrow(() -> new NotFoundException(userId.toString()));
        var saved = userRepository.save(updatedData);
        return userMapper.userToResponseDto(saved);
    }
}
