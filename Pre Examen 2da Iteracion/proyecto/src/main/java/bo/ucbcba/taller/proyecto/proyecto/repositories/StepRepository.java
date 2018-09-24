package bo.ucbcba.taller.proyecto.proyecto.repositories;
import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CORE i7 on 24/05/2017.
 */
@Transactional
public interface StepRepository extends JpaRepository<Step, Integer>  {

    @Query(value = "select * from Step t where t.register_date >= :dateStart and t.register_date <= :dateFin",nativeQuery = true)
    List<Step> findByDates(@Param("dateStart") String dateStart, @Param("dateFin") String dateFin);

    @Query(value = "select * from Step t where t.register_date >= :dateStart and t.register_date <= :dateFin and t.band_id = :idBand",nativeQuery = true)
    List<Step> findByDatesAndBand(@Param("dateStart") String dateStart, @Param("dateFin") String dateFin,@Param("idBand") String idBand);

    @Query(value = "select * from Step t where t.band_id = :bandId",nativeQuery = true)
    List<Step> findByBandId(@Param("bandId") String bandId);


}
