package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.User;
import cl.challenge.tenpo.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
