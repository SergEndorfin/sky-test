package click.itkon.skytest.mappers;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.apifirst.model.UserUpdateRequestDto;
import click.itkon.skytest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User createUserDtoToUser(UserAuthRequestDto createUserRequest) {
        createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        return delegate.createUserDtoToUser(createUserRequest);
    }

    @Override
    public UserResponseDto userToResponseDto(User user) {
        return delegate.userToResponseDto(user);
    }

    @Override
    public User updateUserDtoToUser(UserUpdateRequestDto updateRequestDto) {
        return delegate.updateUserDtoToUser(updateRequestDto);
    }
}
