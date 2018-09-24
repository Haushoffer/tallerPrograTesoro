package bo.ucbcba.taller.proyecto.proyecto.repositories;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface BandRepository extends JpaRepository<Band, Integer> {
    @Query(value = "select * from Band t where t.user_id = :idUser",nativeQuery = true)
    List<Band> findBandByUser(@Param("idUser") String idUser);

}
