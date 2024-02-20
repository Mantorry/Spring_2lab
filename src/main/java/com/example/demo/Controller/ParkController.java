package com.example.demo.Controller;

import com.example.demo.Data.City;
import com.example.demo.Data.Park;
import com.example.demo.Repository.City.CityRepository;
import com.example.demo.Repository.Park.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/park")
@Controller
@SessionAttributes("list")
public class ParkController {
    private final ParkRepository parkRepository;
    private final CityRepository cityRepository;
    @Autowired
    public ParkController(ParkRepository parkRepository, CityRepository cityRepository){
        this.parkRepository = parkRepository;
        this.cityRepository = cityRepository;
    }
    @GetMapping
    public String read(Model model){
        Park park = new Park();
        model.addAttribute("park", park);
        Iterable<Park> parks = parkRepository.findAll();
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);
        model.addAttribute("list", parks);
        return "Park";
    }
    @PostMapping(value = "/save")
    public String save(@Valid @ModelAttribute(value = "park") Park park, BindingResult result, Model model) {
        park.setCity(cityRepository.findById(park.getCity_id()).get());
        if (result.hasErrors()){
            model.addAttribute("cities", cityRepository.findAll());
            return "Park";}
        else{
            parkRepository.savePark(park);
            return "redirect:/park";}
    }
    @PostMapping(value = "/remove")
    public String remove(@ModelAttribute(value = "var") Park park) {
        parkRepository.deletePark(park.getId());
        return "redirect:/park";
    }
    @PostMapping(value = "/refactor_first")
    public String refactor_first(@ModelAttribute(value = "var") Park park, Model model) {
        System.out.println(park);
        park.setCity(cityRepository.findById(park.getCity_id()).get());
        System.out.println(park);
        model.addAttribute("park", park);
        Iterable<Park> parks = parkRepository.findAll();
        model.addAttribute("list", parks);
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);
        return "Park_Refactor";
    }
    @PostMapping(value = "/refactor_second")
    public String refactor_second(@ModelAttribute(value = "park") Park park) {
        park.setCity(cityRepository.findById(park.getCity_id()).get());
        parkRepository.updatePark(park);
        return "redirect:/park";
    }
}
