package bo.ucbcba.taller.proyecto.proyecto.services;

import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import bo.ucbcba.taller.proyecto.proyecto.repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CORE i7 on 24/05/2017.
 */
@Service
public class StepServiceImpl implements StepService {

    private StepRepository stepRepository;

    @Autowired
    @Qualifier(value = "stepRepository")
    public void setStepRepository(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public Iterable<Step> listAllSteps(){
        return stepRepository.findAll();
    }

    @Override
    public Step getStepById(Integer id){
        return stepRepository.findOne(id);
    }

    @Override
    public Step saveStep(Step stepLog) {
        return stepRepository.save(stepLog);
    }

    @Override
    public void deleteStep(Integer id) {
        stepRepository.delete(id);
    }

    @Override
    public List<Step> listAllSearchedSteps(String dateStart, String dateFin){
        return stepRepository.findByDates(dateStart,dateFin);
    }

    @Override
    public List<Step> listAllStepsOfAnBand(String idBand){

        return stepRepository.findByBandId(idBand);

    }
    @Override
    public List<Step> listAllSearchedStepsByBand(String dateStart, String dateFin,String idBand){
        return stepRepository.findByDatesAndBand(dateStart,dateFin,idBand);
    }

}
