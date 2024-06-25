package click.itkon.skytest.controllers;

import click.itkon.skytest.model.ExternalProjectResponseDto;
import click.itkon.skytest.model.ExternalProjectsCreateRequestDto;
import click.itkon.skytest.services.ExternalProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ExternalProjectsController.BASE_URL)
@AllArgsConstructor
public class ExternalProjectsController {

    public static final String BASE_URL = UserController.BASE_URL + "/{userId}/external-projects";

    private final ExternalProjectService externalProjectService;

    @PostMapping
    public ResponseEntity<ExternalProjectResponseDto> addExternalProject(
            @PathVariable("userId") UUID userId,
            @RequestBody ExternalProjectsCreateRequestDto createRequestDto
    ) {
        var addedExternalProjectId = externalProjectService.addExternalProject(userId, createRequestDto);
        return ResponseEntity.created(URI.create(UserController.BASE_URL + "/" + addedExternalProjectId)).build();
    }

    @GetMapping
    public ResponseEntity<List<ExternalProjectResponseDto>> listUsersExternalProjects(@PathVariable("userId") UUID userId) {
        List<ExternalProjectResponseDto> projects = externalProjectService.listUsersExternalProjects(userId);
        return ResponseEntity.ok(projects);
    }
}
