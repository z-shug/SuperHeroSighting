/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.controller;

import com.zls.superHeroSighting.repos.LocationRepository;
import com.zls.superHeroSighting.repos.OrganizationRepository;
import com.zls.superHeroSighting.repos.PowerRepository;
import com.zls.superHeroSighting.repos.SightingRepository;
import com.zls.superHeroSighting.repos.SuperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author zshug
 */
@Controller
public class MainController {
    @Autowired
    LocationRepository location;
    
    @Autowired
    OrganizationRepository organization;
    
    @Autowired
    PowerRepository power;
    
    @Autowired
    SightingRepository sighting;
    
    @Autowired
    SuperRepository supers;
    
    @GetMapping("/")
    public String index(Model model) {
        
        return "index";
    }
    
}
