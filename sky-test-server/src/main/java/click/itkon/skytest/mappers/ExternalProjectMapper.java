package click.itkon.skytest.mappers;

import click.itkon.apifirst.model.ExternalProjectResponseDto;
import click.itkon.apifirst.model.ExternalProjectsCreateRequestDto;
import click.itkon.skytest.domain.ExternalProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ExternalProjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    ExternalProject createExternalProjectDtoToExternalProject(ExternalProjectsCreateRequestDto externalProjectsCreateRequestDto);

    ExternalProjectResponseDto externalProjectToResponseDto(ExternalProject externalProject);
}
