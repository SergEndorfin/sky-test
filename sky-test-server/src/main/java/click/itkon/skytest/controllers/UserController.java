package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.UserAuthRequestDto;
import click.itkon.apifirst.model.UserResponseDto;
import click.itkon.apifirst.model.UserUpdateRequestDto;
import click.itkon.skytest.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(UserController.BASE_URL)
@AllArgsConstructor
public class UserController {

    public static final String BASE_URL = "/v1/users";

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        UserResponseDto savedUser = userService.createUser(userAuthRequestDto);
        return ResponseEntity.created(URI.create(BASE_URL + "/" + savedUser.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("userId") UUID userId,
                                                      @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.ok(userService.updateUser(userId, userUpdateRequestDto));
    }
}
