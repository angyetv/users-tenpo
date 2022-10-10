package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.entities.User;
import cl.challenge.tenpo.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        User user = new User("test", "test", "test");
        user.setRoles(Set.of(new Role(RoleEnum.USER), new Role(RoleEnum.ADMINISTRATOR)));
        when(userService.getByUsername(any())).thenReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername("test");
        assertNotNull(userDetails);
        assertEquals(2, userDetails.getAuthorities().size());
        assertEquals("test", userDetails.getUsername());
        assertEquals("test", userDetails.getPassword());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }
}