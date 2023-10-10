/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.controller;

import com.zls.superHeroSighting.dto.Location;
import com.zls.superHeroSighting.dto.Sighting;
import com.zls.superHeroSighting.repos.LocationRepository;
import com.zls.superHeroSighting.repos.OrganizationRepository;
import com.zls.superHeroSighting.repos.PowerRepository;
import com.zls.superHeroSighting.repos.SightingRepository;
import com.zls.superHeroSighting.repos.SuperRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author zshug
 */
@Controller
public class LocationController {
    @Autowired
    LocationRepository locationRepo;
    
    @Autowired
    OrganizationRepository organizationRepo;
    
    @Autowired
    PowerRepository powerRepo;
    
    @Autowired
    SightingRepository sightingRepo;
    
    @Autowired
    SuperRepository superRepo;
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationRepo.findAll();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @PostMapping("addLocation")
    public String addLocation(Location location){
        locationRepo.save(location);
        return("redirect:/locations");
    }
    
    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model){
        Location location = new Location();
        location = locationRepo.findById(id).orElse(null);
        model.addAttribute("location",location);
        return"editLocation";
    }
    
    @PostMapping("editLocation")
    public String performEditLocation(Location location){
        locationRepo.save(location);
        return "redirect:/locations";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id){
        Location location = locationRepo.findById(id).orElse(null);
        List<Sighting> sightings = sightingRepo.findByLocation(location);
        for(Sighting sighting : sightings){
            sightingRepo.delete(sighting);
        }
        locationRepo.delete(location);
        return "redirect:/locations";
    }
}
