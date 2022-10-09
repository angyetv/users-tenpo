package cl.challenge.tenpo.respositories;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleEnum roleName);
}
