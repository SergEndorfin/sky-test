package click.itkon.skytest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ContactsControllerTest extends BaseTest {

    @Test
    void contacts() throws Exception {
        mockMvc.perform(get(ContactsController.BASE_URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.callToAction").value("Feel free to contact me via: "))
                .andExpect(jsonPath("$.email").value("sergendorfin@gmail.com"));
    }
}
