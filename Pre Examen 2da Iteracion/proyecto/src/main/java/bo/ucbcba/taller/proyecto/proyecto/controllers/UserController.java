package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import bo.ucbcba.taller.proyecto.proyecto.entities.User;
import bo.ucbcba.taller.proyecto.proyecto.services.BandService;
import bo.ucbcba.taller.proyecto.proyecto.services.SecurityService;
import bo.ucbcba.taller.proyecto.proyecto.services.StepService;
import bo.ucbcba.taller.proyecto.proyecto.services.UserService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Juan on 11/05/2017.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BandService bandService;

    @Autowired
    private StepService stepServices;

    @Autowired
    private SecurityService securityService;




    //@Autowired
    //private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationInit(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        ///userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
        model.addAttribute("usuario", user);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    @RequestMapping(value = {"/admin/"}, method = RequestMethod.GET)
    public String admin(Model model) {
        return "welcome";
    }
    @RequestMapping(value = {"/bienvenidos"}, method = RequestMethod.GET)
    public String welcome2(Model model) {
        return "welcomeAdmin";
    }


    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "users";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/myProfile";
    }



    @RequestMapping(value="/myProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        modelAndView.addObject("userName",user.getUsername());
        modelAndView.addObject("weigth",user.getWeigth());
        modelAndView.addObject("heigth",user.getHeigth());
        String sexo;
        if (user.getGender()){
            sexo = "Male";
        }else{
            sexo = "Female";
        }
        modelAndView.addObject("gender",sexo);
        modelAndView.addObject("age",user.getAge());
        modelAndView.setViewName("userShow");
        return modelAndView;
    }
    @RequestMapping(value="/admin/adminMode", method = RequestMethod.GET)
    public ModelAndView adminMode(Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        modelAndView.setViewName("welcomeAdmin");
        return modelAndView;
    }

    @RequestMapping(value="/myBands", method = RequestMethod.GET)
    public ModelAndView viewBands(Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        String idUser= String.valueOf(user.getId());
        List<Band> bandsList = bandService.getBandsByUserId(idUser);
        modelAndView.addObject("bands",bandsList);
        modelAndView.setViewName("bands");
        return modelAndView;
    }

    @RequestMapping(value="/mySteps", method = RequestMethod.GET)
    public ModelAndView viewStats(Model model){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        String idUser= String.valueOf(user.getId());


       DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        String today = dateFormat.format(date);
        String showableDate=today;
        today=today.replace("/", "");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String tomorrow =  dateFormat.format(calendar.getTime());

        tomorrow=tomorrow.replace("/", "");


        Double cantMeters= 0.0;




        List<Band> bandsList = bandService.getBandsByUserId(idUser);
        List<Step> steps=  new ArrayList<Step>();

        for (int j=0;j<bandsList.size();j++){
            String idBand = String.valueOf(bandsList.get(j).getId());

            List<Step> auxSteps = stepServices.listAllSearchedStepsByBand(today,tomorrow,idBand);
            steps.addAll(auxSteps);

        }

        Integer aditionResult=0;

        for (int i =0 ; i<steps.size();i++)
        {
            aditionResult=aditionResult+steps.get(i).getQuantity();
            cantMeters=cantMeters+steps.get(i).getMeters();
        }


        modelAndView.addObject("cantSteps",aditionResult);

        modelAndView.addObject("metersData",cantMeters);

        modelAndView.addObject("showableDate",showableDate);

        modelAndView.setViewName("stepUserShow");


        return modelAndView;
    }
    @RequestMapping(value = "/myStatsSteps", method = RequestMethod.GET)
    public ModelAndView listUserSteps(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        String idUser= String.valueOf(user.getId());

        List<Band> bandsList = bandService.getBandsByUserId(idUser);
        List<Step> steps=  new ArrayList<Step>();
        for (int j=0;j<bandsList.size();j++){
            String idBand = String.valueOf(bandsList.get(j).getId());

            List<Step> auxSteps = stepServices.listAllStepsOfAnBand(idBand);
            steps.addAll(auxSteps);

        }
        modelAndView.addObject("steps",steps);
        modelAndView.setViewName("stepsUser");

        return modelAndView;
    }
    @RequestMapping(value = "/mySearchResults", method = RequestMethod.POST)
    public ModelAndView listResults(String dateStart, String dateEnd,Model model){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        String idUser= String.valueOf(user.getId());

        String dateTime = dateEnd;

        DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-mm-dd");

        DateTime jodatime = dtf.parseDateTime(dateTime);

        DateTime dtPlusOne = jodatime.plusDays(1);

        dateTime =dtPlusOne.toString("YYYY-mm-dd");

        dateStart=dateStart.replace("-", "");
        dateTime=dateTime.replace("-", "");



        List<Band> bandsList = bandService.getBandsByUserId(idUser);
        List<Step> steps=  new ArrayList<Step>();
        for (int j=0;j<bandsList.size();j++){
            String idBand = String.valueOf(bandsList.get(j).getId());

            List<Step> auxSteps = stepServices.listAllSearchedStepsByBand(dateStart,dateTime,idBand);
            steps.addAll(auxSteps);

        }

        modelAndView.addObject("dateStart", dateStart);
        modelAndView.addObject("dateEnd", dateTime);

        modelAndView.addObject("steps",steps);
        modelAndView.setViewName("stepsUser");

        return modelAndView;
    }

}
