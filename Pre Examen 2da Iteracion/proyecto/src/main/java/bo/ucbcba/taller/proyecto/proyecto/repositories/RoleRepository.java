package bo.ucbcba.taller.proyecto.proyecto.repositories;

/**
 * Created by Juan on 11/05/2017.
 */
import bo.ucbcba.taller.proyecto.proyecto.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String role);
}
