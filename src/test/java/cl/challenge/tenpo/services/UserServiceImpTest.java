package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.User;
import cl.challenge.tenpo.respositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByUsername() {
        User user = new User();
        user.setUsername("test");
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        when(userRepository.existsByUsername(any())).thenReturn(true);
        userServiceImp.save(user);
        assertTrue(userServiceImp.existByUsername(user.getUsername()));
        userServiceImp.getByUsername(user.getUsername());
    }
}