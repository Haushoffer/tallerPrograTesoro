package bo.ucbcba.taller.proyecto.proyecto.services;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;

import java.util.List;


public interface BandService {
    Iterable<Band> listAllBands();

    Band getBandById(Integer id);

    Band saveBand(Band band);

    void deleteBand(Integer id);

    List<Band> getBandsByUserId(String userId);
}
