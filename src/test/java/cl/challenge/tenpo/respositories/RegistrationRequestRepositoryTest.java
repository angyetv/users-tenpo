package cl.challenge.tenpo.respositories;

import cl.challenge.tenpo.entities.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegistrationRequestRepositoryTest {

    @Autowired
    private RegistrationRequestRepository registrationRepository;

    @Test
    void existsByEndpoint() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEndpoint("url/test");
        registrationRequest.setMethod("POST");
        registrationRequest.setRemoteUser("user");
        registrationRequest.setStatusCode(200);
        registrationRepository.save(registrationRequest);
        assertTrue(registrationRepository.existsByEndpoint(registrationRequest.getEndpoint()));
    }
}