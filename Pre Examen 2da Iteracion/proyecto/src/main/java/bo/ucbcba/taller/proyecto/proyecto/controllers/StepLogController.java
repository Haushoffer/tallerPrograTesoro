package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import bo.ucbcba.taller.proyecto.proyecto.services.BandService;
import bo.ucbcba.taller.proyecto.proyecto.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by amolina on 15/05/17.
 */

@RestController
public class StepLogController {

    private StepService stepService;
    private BandService bandService;

    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }

    @Autowired
    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }

    @RequestMapping(value = "/API/stepLog/{register}/{meters}", method = RequestMethod.POST)
    public Step saveStep(@Valid Step step,@PathVariable Long register,@PathVariable Double meters, BindingResult bindingResult, Model model) {
        //establezco el band por el bandId del step
        int bandId=step.getBandId();
        Band band=bandService.getBandById(bandId);
        step.setBand(band);
        step.setMeters(meters);
        //establezco la fecha de subida
        Date uploadDate=new Date(System.currentTimeMillis());
        step.setUploadDate(uploadDate);
        //establezco la fecha de registro, que se recibio como PathVariable
        Date registerDate=new Date(register);
        step.setRegisterDate(registerDate);
        //guardo el Step en la base de datos
        stepService.saveStep(step);
        return step;
    }

    @RequestMapping(value = "/API/stepLogs", method = RequestMethod.GET)
    public Iterable<Step> listStep( ) {
        Iterable<Step>stepLogs=stepService.listAllSteps();
        return stepLogs;
    }

    @RequestMapping(value = "/API/delete/{id}", method = RequestMethod.DELETE)
    public Step deleteStep(@PathVariable Integer id) {
        stepService.deleteStep(id);
        return null;
    }

    @RequestMapping(value = "/API/stepLog/{id}", method = RequestMethod.GET)
    public Step getStep(@PathVariable Integer id)
    {
        Step step=stepService.getStepById(id);
        step.setBandId(step.getBand().getId());
        return step;
    }

   /* @RequestMapping(value = "/API/edit/{id}", method = RequestMethod.PUT)
    public Step editStep(@Valid Integer steps,@PathVariable Integer id) {
        Step s=stepLogService.getStepLogById(id);
        s.setSteps(steps);
        stepLogService.saveStepLog(s);
        return s;
    }*/


}
