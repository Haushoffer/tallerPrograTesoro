package bo.ucbcba.taller.proyecto.proyecto.services;

import bo.ucbcba.taller.proyecto.proyecto.entities.User;

/**
 * Created by Juan on 11/05/2017.
 */
public interface UserService {
    void save(User user);

    User findByUsername(String username);

    Iterable<User> listAllUsers();
    User getUserById(Integer id);
}
