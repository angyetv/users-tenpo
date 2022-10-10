package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.RegistrationRequest;
import cl.challenge.tenpo.respositories.RegistrationRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RegistrationRequestServiceImpTest {

    @InjectMocks
    private RegistrationRequestServiceImp registrationRequestServiceImp;

    @Mock
    private RegistrationRequestRepository registrationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllRegistrations() {
        Page<RegistrationRequest> pages = new PageImpl<>(List.of(new RegistrationRequest()),
                PageRequest.of(0, 10), 0);
        registrationRequestServiceImp.save(new RegistrationRequest());
        when(registrationRepository.findAll((Pageable) ArgumentMatchers.<Page<RegistrationRequest>>any())).thenReturn(pages);
        Page<RegistrationRequest> response = registrationRequestServiceImp.findAllRegistrations(PageRequest.of(0, 10));
        assertEquals(1, response.getTotalPages());
    }
}