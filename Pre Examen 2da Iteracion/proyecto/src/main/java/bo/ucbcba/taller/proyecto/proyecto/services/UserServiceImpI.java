package bo.ucbcba.taller.proyecto.proyecto.services;

import bo.ucbcba.taller.proyecto.proyecto.entities.Role;
import bo.ucbcba.taller.proyecto.proyecto.entities.User;
import bo.ucbcba.taller.proyecto.proyecto.repositories.RoleRepository;
import bo.ucbcba.taller.proyecto.proyecto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


/**
 * Created by Juan on 11/05/2017.
 */
@Service
public class UserServiceImpI implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Iterable<User> listAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }
}
