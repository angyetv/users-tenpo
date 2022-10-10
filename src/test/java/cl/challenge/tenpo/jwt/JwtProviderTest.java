package cl.challenge.tenpo.jwt;

import cl.challenge.tenpo.entities.Role;
import cl.challenge.tenpo.entities.User;
import cl.challenge.tenpo.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    @InjectMocks
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtProvider, "secret", "secret");
        ReflectionTestUtils.setField(jwtProvider, "expiration", 6000);
    }

    @Test
    void generateToken() {
        String token = jwtProvider.generateToken(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                User user = new User("test", "test", "test");
                user.setRoles(Set.of(new Role(RoleEnum.USER), new Role(RoleEnum.ADMINISTRATOR)));
                return MainUser.build(user);
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        });

        assertTrue(jwtProvider.validateToken(token));
        assertEquals("test", jwtProvider.getUsernameFromToken(token));
    }

    @Test
    void validateToken() {
        assertFalse(jwtProvider.validateToken(null));
        assertFalse(jwtProvider.validateToken("malformado"));
        assertFalse(jwtProvider.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOi." +
                "mprxHQ7-3Mq950vF-kiyvVy1lPxwcdSrgNcutg9CiA"));
    }
}