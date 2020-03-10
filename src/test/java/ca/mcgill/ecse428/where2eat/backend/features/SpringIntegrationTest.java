package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.Where2eatBackendApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration(
        classes = Where2eatBackendApplication.class)
@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class SpringIntegrationTest {

}
