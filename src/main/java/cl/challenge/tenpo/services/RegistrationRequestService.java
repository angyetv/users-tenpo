package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.RegistrationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegistrationRequestService {

    Page<RegistrationRequest> findAllRegistrations(Pageable pageable);

    void save(RegistrationRequest request);
}
