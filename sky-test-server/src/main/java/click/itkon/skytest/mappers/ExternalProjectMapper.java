package click.itkon.skytest.mappers;

import click.itkon.skytest.domain.ExternalProject;
import click.itkon.skytest.model.ExternalProjectResponseDto;
import click.itkon.skytest.model.ExternalProjectsCreateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ExternalProjectMapper {

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    ExternalProject createExternalProjectDtoToExternalProject(ExternalProjectsCreateRequestDto externalProjectsCreateRequestDto);

    ExternalProjectResponseDto externalProjectToResponseDto(ExternalProject externalProject);
}
