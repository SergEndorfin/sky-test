package click.itkon.skytest.services;

import click.itkon.skytest.model.ExternalProjectResponseDto;
import click.itkon.skytest.model.ExternalProjectsCreateRequestDto;
import click.itkon.skytest.domain.User;
import click.itkon.skytest.exceptions.NotFoundException;
import click.itkon.skytest.mappers.ExternalProjectMapper;
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

    @Override
    public UUID addExternalProject(UUID userId,
                                   ExternalProjectsCreateRequestDto createRequestDto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId.toString()));

        var projectEntity = externalProjectMapper.createExternalProjectDtoToExternalProject(createRequestDto);
        projectEntity.setUser(user);
        user.getExternalProjects().add(projectEntity);

        userRepository.save(user);
        return projectEntity.getId();
    }

    @Override
    public List<ExternalProjectResponseDto> listUsersExternalProjects(UUID userId) {
        return userRepository.findById(userId)
                .map(User::getExternalProjects)
                .map(projects -> projects.stream().map(externalProjectMapper::externalProjectToResponseDto).toList())
                .orElseThrow(() -> new NotFoundException(userId.toString()));
    }
}
