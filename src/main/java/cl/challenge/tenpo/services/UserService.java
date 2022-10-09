package cl.challenge.tenpo.services;

import cl.challenge.tenpo.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByUsername(String username);

    boolean existByUsername(String username);

    void save(User user);
}
