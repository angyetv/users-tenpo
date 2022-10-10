package cl.challenge.tenpo.respositories;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.enums.RoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByRoleName() {
        assertTrue(roleRepository.findByRoleName(RoleEnum.USER).isPresent());
        Role role = new Role(RoleEnum.USER);
        roleRepository.save(role);
    }
}