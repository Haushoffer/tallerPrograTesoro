package bo.ucbcba.taller.proyecto.proyecto.repositories;

/**
 * Created by Juan on 11/05/2017.
 */
import bo.ucbcba.taller.proyecto.proyecto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}


