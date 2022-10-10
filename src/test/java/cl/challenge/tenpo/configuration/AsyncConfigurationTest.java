package cl.challenge.tenpo.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.*;

class AsyncConfigurationTest {

    @InjectMocks
    private AsyncConfiguration asyncConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void asyncExecutor() {
        Executor executor = asyncConfiguration.asyncExecutor();
        assertTrue(executor.toString().contains("@"));
    }
}