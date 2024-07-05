package click.itkon.skytest.controllers;

import click.itkon.apifirst.model.ContactDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ContactsController.BASE_URL)
public class ContactsController {

    public static final String BASE_URL = "/v1/contacts";

    @GetMapping
    public ResponseEntity<ContactDto> contacts() {
        return ResponseEntity.ok(ContactDto.builder()
                .callToAction("Feel free to contact me via: ")
                .email("sergendorfin@gmail.com").build());
    }
}
