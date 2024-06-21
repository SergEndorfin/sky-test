package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.User;
import click.itkon.skytest.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "/v1/users";

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

}
