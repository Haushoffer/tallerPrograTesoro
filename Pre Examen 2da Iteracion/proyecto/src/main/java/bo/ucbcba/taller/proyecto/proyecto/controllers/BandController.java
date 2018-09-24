package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.services.BandService;
import bo.ucbcba.taller.proyecto.proyecto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BandController {
    private BandService bandService;
    private UserService userService;


    @Autowired
    public void setBandService(BandService bandService) {
        this.bandService = bandService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/bands", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("bands", bandService.listAllBands());
        return "bands";
    }

    @RequestMapping("band/{id}")
    public String showBand(@PathVariable Integer id, Model model) {
        model.addAttribute("band", bandService.getBandById(id));
        return "bandShow";
    }

    @RequestMapping("band/edit/{id}")
    public String editBand(@PathVariable Integer id, Model model) {
        model.addAttribute("band", bandService.getBandById(id));
        model.addAttribute("users",userService.listAllUsers());
        return "bandForm";
    }

    @RequestMapping("band/new")
    public String newBand(Model model) {
        model.addAttribute("band", new Band());
        model.addAttribute("users",userService.listAllUsers());
        return "bandForm";
    }

    @RequestMapping(value = "band", method = RequestMethod.POST)
    public String saveBand(Band band) {
        bandService.saveBand(band);
        return "redirect:/band/" + band.getId();
    }

    @RequestMapping("band/delete/{id}")
    public String deleteBand(@PathVariable Integer id) {
        bandService.deleteBand(id);
        return "redirect:/bands";
    }
}
