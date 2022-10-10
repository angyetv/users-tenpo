package cl.challenge.tenpo.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtEntryPointTest {

    @InjectMocks
    private JwtEntryPoint jwtEntryPoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void commence() {
        assertThrows(NullPointerException.class, () -> jwtEntryPoint.commence(null, null, null));
    }
}