package click.itkon.skytest.controllers;

import click.itkon.skytest.domain.User;
import click.itkon.skytest.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles({"local"})
public class BaseTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    Filter validationFilter;

    @Autowired
    ObjectMapper objectMapper;

    public MockMvc mockMvc;

    User testUserEntity;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(validationFilter)
                .build();
        testUserEntity = userRepository.findAll().iterator().next();
    }
}
