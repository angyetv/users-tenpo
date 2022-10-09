package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.RegistrationRequest;
import cl.challenge.tenpo.respositories.RegistrationRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationRequestServiceImp implements RegistrationRequestService {

    private final RegistrationRequestRepository registrationRepository;

    @Override
    public Page<RegistrationRequest> findAllRegistrations(Pageable pageable) {
        return registrationRepository.findAll(pageable);
    }

    @Override
    @Transactional
    @Async("asyncExecutor")
    public void save(RegistrationRequest request) {
        registrationRepository.save(request);
    }
}
