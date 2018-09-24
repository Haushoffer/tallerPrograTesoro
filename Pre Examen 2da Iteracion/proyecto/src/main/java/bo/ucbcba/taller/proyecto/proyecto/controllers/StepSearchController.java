package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.entities.Step;
import bo.ucbcba.taller.proyecto.proyecto.services.StepService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CORE i7 on 27/05/2017.
 */
@Controller
public class StepSearchController {
    @Autowired
    private StepService stepService;


    @RequestMapping(value = "/admin/searchResults", method = RequestMethod.POST)
    public String listResults(String dateStart, String dateEnd,Model model){


       /*String dateIni ="20170526";
        String dateFin ="20170528";*//*al configurar dsde view string-int +1 de ahi int string*/

        String dateTime = dateEnd;

        DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd");

        DateTime jodatime = dtf.parseDateTime(dateTime);

        DateTime dtPlusOne = jodatime.plusDays(1);

        dateTime =dtPlusOne.toString("YYYY-MM-dd");

        dateStart=dateStart.replace("-", "");
        dateTime=dateTime.replace("-", "");

        model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateEnd", dateTime);

        model.addAttribute("steps", stepService.listAllSearchedSteps(dateStart,dateTime));


        return "steps";
    }
}
