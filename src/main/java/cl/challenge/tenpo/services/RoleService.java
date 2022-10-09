package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.enums.RoleEnum;
import cl.challenge.tenpo.exception.BusinessException;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleService {

    Role getByRoleName(RoleEnum roleName) throws BusinessException;

    List<Role> getAllRoles();

    @Transactional
    void save(Role user);

    void checkRoles();
}
