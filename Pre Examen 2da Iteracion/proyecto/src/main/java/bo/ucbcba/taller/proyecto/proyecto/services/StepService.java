package bo.ucbcba.taller.proyecto.proyecto.services;

import bo.ucbcba.taller.proyecto.proyecto.entities.Step;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CORE i7 on 24/05/2017.
 */
public interface StepService {

    Iterable<Step> listAllSteps();

    Step getStepById(Integer id);


    Step saveStep(Step step);

    List<Step> listAllSearchedSteps(String dateStart, String dateFin);

    List<Step> listAllSearchedStepsByBand(String dateStart, String dateFin,String idBand);



    void deleteStep(Integer id);

    List<Step> listAllStepsOfAnBand(String idBand);


}
