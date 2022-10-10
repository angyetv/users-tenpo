package cl.challenge.tenpo.cron;

import cl.challenge.tenpo.exception.ExternalServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExternalServicePercentageTest {

    @InjectMocks
    private ExternalServicePercentage externalServicePercentage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @RepeatedTest(value = 3)
    void calculate() {
        assertThrows(ExternalServiceException.class, () ->
                        externalServicePercentage.calculate(4, 5),
                "Error al consumir el servicio externo");
    }

    @Test
    void calculatePercentage() {
        assertThrows(ExternalServiceException.class, () ->
                        externalServicePercentage.calculate(4, 5),
                "Error al consumir el servicio externo");
        externalServicePercentage.calculatePercentage();
        Float newValue = externalServicePercentage.calculate(5, 5);
        assertEquals(10.81f, newValue);
    }
}