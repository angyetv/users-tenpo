package cl.challenge.tenpo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsersTenpoApplicationTests {

    @InjectMocks
    private UsersTenpoApplication usersTenpoApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
        assertNotNull(usersTenpoApplication);
    }
}
