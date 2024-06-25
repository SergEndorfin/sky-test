package click.itkon.skytest.mappers;

import click.itkon.skytest.model.ExternalProjectsCreateRequestDto;
import click.itkon.skytest.domain.ExternalProject;
import click.itkon.skytest.domain.User;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExternalProjectMapperTest {

    ExternalProjectMapper externalProjectMapper = new ExternalProjectMapperImpl();

    @Test
    void createExternalProjectDtoToExternalProject() {
        var requestDto = ExternalProjectsCreateRequestDto.builder()
                .name("Project 1")
                .description("First project description")
                .build();
        var entity = externalProjectMapper.createExternalProjectDtoToExternalProject(requestDto);

        assertEquals(requestDto.getName(), entity.getName());
        assertEquals(requestDto.getDescription(), entity.getDescription());
    }

    @Test
    void externalProjectToResponseDto() {
        var entity = ExternalProject.builder()
                .id(UUID.randomUUID())
                .name("Project 1")
                .description("First project description")
                .user(User.builder().id(UUID.randomUUID()).build())
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now())
                .build();
        var responseDto = externalProjectMapper.externalProjectToResponseDto(entity);

        assertEquals(entity.getId(), responseDto.getId());
        assertEquals(entity.getName(), responseDto.getName());
        assertEquals(entity.getDescription(), responseDto.getDescription());
        assertEquals(entity.getDateCreated(), responseDto.getDateCreated());
        assertEquals(entity.getDateUpdated(), responseDto.getDateUpdated());
    }
}
