/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.controller;

import com.zls.superHeroSighting.dto.Location;
import com.zls.superHeroSighting.dto.Sighting;
import com.zls.superHeroSighting.dto.Super;
import com.zls.superHeroSighting.repos.LocationRepository;
import com.zls.superHeroSighting.repos.OrganizationRepository;
import com.zls.superHeroSighting.repos.PowerRepository;
import com.zls.superHeroSighting.repos.SightingRepository;
import com.zls.superHeroSighting.repos.SuperRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class SightingController {
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
    
    @GetMapping("sightings")
    public String displaySightings(Model model){
        List<Sighting> sightings = sightingRepo.findAll();
        List<Super> supers = superRepo.findAll();
        List<Location> locations = locationRepo.findAll();
        model.addAttribute("sightings",sightings);
        model.addAttribute("supers",supers);
        model.addAttribute("locations",locations);
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSighting(String superid, String locationid, String date,HttpServletRequest request){ 
        Sighting sighting = new Sighting();
        Super superObj = superRepo.findById(Integer.parseInt(superid)).orElse(null);
        Location location = locationRepo.findById(Integer.parseInt(locationid)).orElse(null);
        LocalDate formattedDate = LocalDate.parse(date);
        sighting.setSuperObj(superObj);
        sighting.setLocation(location);
        sighting.setDate(formattedDate);
        sightingRepo.save(sighting);
        return "redirect:/sightings";
    }
    
    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model){
        Sighting sighting = sightingRepo.findById(id).orElse(null);
        List<Super> supers = superRepo.findAll();
        List<Location> locations = locationRepo.findAll();
        model.addAttribute("sighting",sighting);
        model.addAttribute("supers",supers);
        model.addAttribute("locations",locations);
        return "editSighting";
    }
    
    @PostMapping("editSighting")
    public String performEditSighting(Sighting sighting,HttpServletRequest request){
        String superId = request.getParameter("superId");
        String locationId = request.getParameter("locationId");
        Super superObj = superRepo.findById(Integer.parseInt(superId)).orElse(null);
        Location locationObj = locationRepo.findById(Integer.parseInt(locationId)).orElse(null);
        sighting.setSuperObj(superObj);
        sighting.setLocation(locationObj);
        sightingRepo.save(sighting);
        return"redirect:/sightings";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting (Integer id){
        Sighting sighting = sightingRepo.findById(id).orElse(null);
        sightingRepo.delete(sighting);
        return"redirect:/sightings";
    }
}
