package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.enums.RoleEnum;
import cl.challenge.tenpo.exception.BusinessException;
import cl.challenge.tenpo.exception.RoleNotFoundException;
import cl.challenge.tenpo.respositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getByRoleName(RoleEnum roleName) throws BusinessException {
        return roleRepository.findByRoleName(roleName).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Role user) {
        roleRepository.save(user);
    }

    /**
     * Inicializa la bd con los roles por defecto.
     */
    @Override
    @Transactional
    public void checkRoles() {
        if (getAllRoles().isEmpty()) {
            save(new Role(RoleEnum.USER));
            save(new Role(RoleEnum.ADMINISTRATOR));
        }
    }
}
