/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.controller;

import com.zls.superHeroSighting.dto.Organization;
import com.zls.superHeroSighting.dto.Power;
import com.zls.superHeroSighting.dto.Super;
import com.zls.superHeroSighting.repos.LocationRepository;
import com.zls.superHeroSighting.repos.OrganizationRepository;
import com.zls.superHeroSighting.repos.PowerRepository;
import com.zls.superHeroSighting.repos.SightingRepository;
import com.zls.superHeroSighting.repos.SuperRepository;
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
public class SupersController {
    @Autowired
    LocationRepository location;
    
    @Autowired
    OrganizationRepository organizationRepo;
    
    @Autowired
    PowerRepository powerRepo;
    
    @Autowired
    SightingRepository sighting;
    
    @Autowired
    SuperRepository superRepo;
    
    @GetMapping("supers")
    public String displaySupers(Model model) {
        List<Super> supers = superRepo.findAll();
        List<Power> powers = powerRepo.findAll();
        model.addAttribute("supers",supers);
        model.addAttribute("powers",powers);
        return "supers";
    }
    
    @PostMapping("addSuper")
    public String addSuper(Super superHero, HttpServletRequest request) {
        String powerId = request.getParameter("powerid");
        if(powerId != null) {
            Power power = powerRepo.findById(Integer.parseInt(powerId)).orElse(null);
            superHero.setPower(power);
        }
        
        superRepo.save(superHero);
        return "redirect:/supers";
    }
    
    @GetMapping("editSuper")
    public String editSuper(Integer id, Model model){
        Super supers = new Super();
        supers = superRepo.findById(id).orElse(null);
        List<Power> powers = powerRepo.findAll();
        model.addAttribute("super", supers);
        model.addAttribute("powers", powers);
        return "editSuper";
    }
    
    @PostMapping("editSuper") 
    public String performEditSuper(Super superObj, HttpServletRequest request){
        String powerId = request.getParameter("powerId");
        
        if(powerId != null) {
            superObj.setPower(powerRepo.findById(Integer.parseInt(powerId)).orElse(null));
        }
        
        
        superRepo.save(superObj);
        return "redirect:/supers";
    }
    
    @GetMapping("/deleteSuper")
    public String deleteSuper(Integer id){
       Super superObj = superRepo.findById(id).orElse(null);
       List<Organization> organizations = organizationRepo.findBySupers(superObj);
       
       for (Organization organization : organizations) {
           organization.getSupers().remove(superObj);
       }
       
       superRepo.deleteById(id);
       return "redirect:/supers";
    }
    
    @GetMapping("/superDetail")
    public String superDetail (Integer id, Model model){
        Super superObj = superRepo.findById(id).orElse(null);
        model.addAttribute("super", superObj);
        return "superDetail";  
    }
}
