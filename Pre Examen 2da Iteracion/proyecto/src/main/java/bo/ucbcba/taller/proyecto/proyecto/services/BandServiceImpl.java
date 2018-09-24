package bo.ucbcba.taller.proyecto.proyecto.services;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BandServiceImpl implements BandService{

    private BandRepository bandRepository;

    @Autowired
    @Qualifier(value = "bandRepository")
    public void setBandRepositoryRepository(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }



    @Override
    public Iterable<Band> listAllBands() {
        return bandRepository.findAll();
    }

    @Override
    public Band getBandById(Integer id) {
        return bandRepository.findOne(id);
    }

    @Override
    public Band saveBand(Band band) {
        return bandRepository.save(band);
    }

    @Override
    public void deleteBand(Integer id) {
        bandRepository.delete(id);
    }

    @Override
    public List<Band> getBandsByUserId(String userId){
        return bandRepository.findBandByUser(userId);
    }
}

