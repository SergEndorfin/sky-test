package click.itkon.skytest.services;

import click.itkon.skytest.model.ExternalProjectResponseDto;
import click.itkon.skytest.model.ExternalProjectsCreateRequestDto;

import java.util.List;
import java.util.UUID;

public interface ExternalProjectService {

    UUID addExternalProject(UUID userId, ExternalProjectsCreateRequestDto externalProjectsCreateRequestDto);

    List<ExternalProjectResponseDto> listUsersExternalProjects(UUID userId);
}
