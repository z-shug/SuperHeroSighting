/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.controller;

import com.zls.superHeroSighting.dto.Organization;
import com.zls.superHeroSighting.dto.Super;
import com.zls.superHeroSighting.repos.LocationRepository;
import com.zls.superHeroSighting.repos.OrganizationRepository;
import com.zls.superHeroSighting.repos.PowerRepository;
import com.zls.superHeroSighting.repos.SightingRepository;
import com.zls.superHeroSighting.repos.SuperRepository;
import java.util.ArrayList;
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
public class OrganizationController {
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
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationRepo.findAll();
        List<Super> supers = superRepo.findAll();
        model.addAttribute("organizations", organizations);
        model.addAttribute("supers", supers);
        return ("organizations");
    }
    
    @PostMapping("addOrganization")
    public String addOrganization(Organization organization, HttpServletRequest request){
        String[] superIds = request.getParameterValues("superid");
        
        List<Super> supers = new ArrayList<>();
        for(String superId : superIds){
            supers.add(superRepo.findById(Integer.parseInt(superId)).orElse(null));
        }
        organization.setSupers(supers);
        organizationRepo.save(organization);
        return "redirect:/organizations";  
    }
    
    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model){
        Organization organization = new Organization();
        organization = organizationRepo.findById(id).orElse(null);
        List<Super> supers = superRepo.findAll();
        model.addAttribute("organization",organization);
        model.addAttribute("supers",supers);
        return "editOrganization";
    }
    
    @PostMapping("editOrganization")
    public String performEditOrganization(Organization organization, HttpServletRequest request){
        
        String[] superIds = request.getParameterValues("superid");
        List<Super> supers = new ArrayList<>();
        for(String superId : superIds){
             supers.add(superRepo.findById(Integer.parseInt(superId)).orElse(null));
        }
        organization.setSupers(supers);
        organizationRepo.save(organization);
        return"redirect:/organizations";
    }
    
    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id){
        Organization organization = organizationRepo.findById(id).orElse(null);
        List<Super> supers = superRepo.findByOrganization(organization);
        
        for(Super superObj:supers){
            organization.getSupers().remove(superObj);
        }
        
        
        organizationRepo.delete(organization);
        return"redirect:/organizations";
    }
    
    @GetMapping("organizationDetails")
    public String organizationDetails(Integer id, Model model) {
        Organization organization = organizationRepo.findById(id).orElse(null);
        model.addAttribute("organization", organization);
        return "organizationDetails";
    }
}
