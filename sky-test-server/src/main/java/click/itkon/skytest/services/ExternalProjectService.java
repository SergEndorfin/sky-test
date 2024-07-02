package click.itkon.skytest.services;

import click.itkon.apifirst.model.ExternalProjectResponseDto;
import click.itkon.apifirst.model.ExternalProjectsCreateRequestDto;

import java.util.List;
import java.util.UUID;

public interface ExternalProjectService {

    UUID addExternalProject(UUID userId, ExternalProjectsCreateRequestDto externalProjectsCreateRequestDto);

    List<ExternalProjectResponseDto> listUsersExternalProjects(UUID userId);

    ExternalProjectResponseDto getExternalProjectByUserIdAndProjectId(UUID userId, UUID projectId);
}
