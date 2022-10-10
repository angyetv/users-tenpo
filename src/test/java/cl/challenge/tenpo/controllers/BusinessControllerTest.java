package cl.challenge.tenpo.controllers;

import cl.challenge.tenpo.cron.ExternalServicePercentage;
import cl.challenge.tenpo.dtos.PagedResponse;
import cl.challenge.tenpo.dtos.RegistrationOutDTO;
import cl.challenge.tenpo.entities.RegistrationRequest;
import cl.challenge.tenpo.services.RegistrationRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BusinessControllerTest {

    @InjectMocks
    private BusinessController businessController;

    @Mock
    private RegistrationRequestService registrationService;

    @Mock
    private ExternalServicePercentage externalServicePercentage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRecords() {
        Page<RegistrationRequest> pages = new PageImpl<>(List.of(new RegistrationRequest(), new RegistrationRequest()), PageRequest.of(0, 10), 0);
        when(registrationService.findAllRegistrations(any())).thenReturn(pages);
        ResponseEntity<PagedResponse<RegistrationOutDTO>> response = businessController.getRecords(0, 10);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void addTwoNumbers() {
        ResponseEntity<Float> response = businessController.addTwoNumbers(1, 5);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(0, response.getBody());
    }
}