package cl.challenge.tenpo.respositories;

import cl.challenge.tenpo.entities.RegistrationRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRequestRepository extends PagingAndSortingRepository<RegistrationRequest, Long> {
    boolean existsByEndpoint(String endpoint);
}
