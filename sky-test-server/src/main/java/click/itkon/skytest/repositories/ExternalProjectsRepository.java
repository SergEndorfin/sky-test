package click.itkon.skytest.repositories;

import click.itkon.skytest.domain.ExternalProject;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExternalProjectsRepository extends CrudRepository<ExternalProject, UUID> {

    Optional<ExternalProject> findByUserIdAndId(UUID userId, UUID projectId);
}
