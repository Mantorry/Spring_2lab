package com.example.demo.Controller;

import com.example.demo.Data.City;
import com.example.demo.Repository.City.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("city")
@SessionAttributes("list")
@Controller
public class CityController {
    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {this.cityRepository = cityRepository;}

    @GetMapping
    public String show(Model model) {
        City city = new City();
        model.addAttribute("city",city);
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("list", cities);
        return "City";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("city") City city, BindingResult result) {
        if(result.hasErrors()){
            return "City";
        }
        else{
            cityRepository.saveCity(city);return "redirect:/city";}
    }

    @PostMapping(value = "/remove")
    public String remove(@ModelAttribute(value = "var") City city) {
        cityRepository.deleteCity(city.getId());
        return "redirect:/city";
    }

    @PostMapping(value = "/refactor_first")
    public String update_start(@ModelAttribute(value = "var") City city, Model model) {
        model.addAttribute("city", city);
        Iterable<City> cities = cityRepository.findAll();
        model.addAttribute("list", cities);
        return "City_Refactor";
    }

    @PostMapping(value = "/refactor_second")
    public String update_end(@ModelAttribute(value = "artist") City city) {
        cityRepository.updateCity(city);
        return "redirect:/city";
    }
}
