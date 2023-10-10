/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.controller;

import com.zls.superHeroSighting.dto.Power;
import com.zls.superHeroSighting.dto.Super;
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
public class PowerController {
    @Autowired
    LocationRepository location;
    
    @Autowired
    OrganizationRepository organization;
    
    @Autowired
    PowerRepository powerRepo;
    
    @Autowired
    SightingRepository sighting;
    
    @Autowired
    SuperRepository superRepo;
    
    @GetMapping("powers")
    public String displayPowers(Model model) {
        List<Power> powers = powerRepo.findAll();
        model.addAttribute("powers",powers);
        return "powers";
    }
    
    @PostMapping("addPower")
    public String addPower(String powerName){
        Power power = new Power();
        power.setName(powerName);
        powerRepo.save(power);
        return "redirect:/powers";
    }
    
    @GetMapping("deletePower")
    public String deletePower(Integer id) {
        Power power = powerRepo.findById(id).orElse(null);
        List<Super> supers = superRepo.findByPower(power);
        for (Super superObj : supers){
            
            superObj.setPower(null);
            superRepo.save(superObj);
        }
        powerRepo.deleteById(id);
        return "redirect:/powers";
    }
    
    @GetMapping("editPower")
    public String editPower(Integer id, Model model) {
        Power power = new Power();
           power = powerRepo.findById(id).orElse(null);
        model.addAttribute("power", power);
        return "editPower";
    }
    
    @PostMapping("editPower")
    public String performEditPower(Integer id, String powerName) {
        Power power = new Power();
        power.setPowerid(id);
        power.setName(powerName);
        powerRepo.save(power);
        return "redirect:/powers";
    }
}
