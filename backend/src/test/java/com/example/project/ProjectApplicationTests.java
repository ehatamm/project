package com.example.project;

import com.example.project.config.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class ProjectApplicationTests {

    @Test
    void contextLoads() {
    }

}
