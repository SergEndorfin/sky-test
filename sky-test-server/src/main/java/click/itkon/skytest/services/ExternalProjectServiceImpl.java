package click.itkon.skytest.services;

import click.itkon.apifirst.model.ExternalProjectResponseDto;
import click.itkon.apifirst.model.ExternalProjectsCreateRequestDto;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.mappers.ExternalProjectMapper;
import click.itkon.skytest.repositories.ExternalProjectsRepository;
import click.itkon.skytest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalProjectServiceImpl implements ExternalProjectService {

    private final UserRepository userRepository;
    private final ExternalProjectMapper externalProjectMapper;
    private final ExternalProjectsRepository externalProjectsRepository;

    @Override
    public UUID addExternalProject(UUID userId,
                                   ExternalProjectsCreateRequestDto createRequestDto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId.toString()));

        var projectEntity = externalProjectMapper.createExternalProjectDtoToExternalProject(createRequestDto);
        projectEntity.setUser(user);

        externalProjectsRepository.save(projectEntity);
        return projectEntity.getId();
    }

    @Override
    public List<ExternalProjectResponseDto> listUsersExternalProjects(UUID userId) {
        return userRepository.findById(userId)
                .map(User::getExternalProjects)
                .map(projects -> projects.stream().map(externalProjectMapper::externalProjectToResponseDto).toList())
                .orElseThrow(() -> new NotFoundException(userId.toString()));
    }

    @Override
    public ExternalProjectResponseDto getExternalProjectByUserIdAndProjectId(UUID userId, UUID projectId) {
        return externalProjectsRepository.findByUserIdAndId(userId, projectId)
                .map(externalProjectMapper::externalProjectToResponseDto)
                .orElseThrow(() -> new NotFoundException("User id " + userId.toString() +
                        ". Project id " + projectId.toString()));
    }
}
