package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import bo.ucbcba.taller.proyecto.proyecto.entities.User;
import bo.ucbcba.taller.proyecto.proyecto.services.BandService;
import bo.ucbcba.taller.proyecto.proyecto.services.StepService;
import bo.ucbcba.taller.proyecto.proyecto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CORE i7 on 24/05/2017.
 */
@Controller
public class StepController {
    private StepService stepService;



    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }


    @RequestMapping(value = "/admin/steps", method = RequestMethod.GET)
    public String list(Model model
            /*, @PathVariable("dateStart") Date dateStart, @PathVariable("dateEnd") Date dateEnd*/) {

        model.addAttribute("steps", stepService.listAllSteps());
        /*model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateFin", dateEnd);*/
        return "steps";
    }



}
