package click.itkon.skytest.repositories;

import click.itkon.skytest.config.RepositoryTestConfig;
import click.itkon.skytest.domain.ExternalProject;
import click.itkon.skytest.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ComponentScan("click.itkon.skytest.bootstrap")
@Import(RepositoryTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExternalProjectsRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ExternalProjectsRepository projectsRepository;

    @Test
    void findByUserIdAndId() {
        User user = userRepository.findAll().get(0);
        ExternalProject project = user.getExternalProjects().get(0);
        Optional<ExternalProject> foundProject = projectsRepository.findByUserIdAndId(user.getId(), project.getId());
        assertNotNull(foundProject.get());
        assertEquals(user.getId(), foundProject.get().getUser().getId());
    }
}
