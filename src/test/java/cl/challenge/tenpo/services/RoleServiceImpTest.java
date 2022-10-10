package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.enums.RoleEnum;
import cl.challenge.tenpo.exception.BusinessException;
import cl.challenge.tenpo.exception.RoleNotFoundException;
import cl.challenge.tenpo.respositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RoleServiceImpTest {

    @InjectMocks
    private RoleServiceImp roleServiceImp;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByRoleName() throws BusinessException {
        when(roleRepository.findByRoleName(any())).thenReturn(Optional.of(new Role(RoleEnum.USER)));
        Role role = roleServiceImp.getByRoleName(RoleEnum.USER);
        assertNotNull(role);
        assertEquals(RoleEnum.USER, role.getRoleName());
    }

    @Test
    void getByRoleNameError() {
        when(roleRepository.findByRoleName(any())).thenReturn(Optional.empty());
        assertThrows(RoleNotFoundException.class, () -> roleServiceImp.getByRoleName(RoleEnum.USER));
    }

    @Test
    void getAllRoles() {
        when(roleRepository.findAll()).thenReturn(List.of(new Role()));
        List<Role> roles = roleServiceImp.getAllRoles();
        assertEquals(1, roles.size());
        roleServiceImp.save(roles.get(0));
    }

    @Test
    void checkRoles() {
        when(roleRepository.findAll()).thenReturn(List.of(new Role()));
        roleServiceImp.checkRoles();
        when(roleRepository.findAll()).thenReturn(List.of());
        roleServiceImp.checkRoles();
    }
}