/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.dto;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author zshug
 */
@Entity
public class Super {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int superid;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name ="powerid")
    private Power power;
    
    @ManyToMany(mappedBy= "supers")
    private List<Organization> organization;

    public int getSuperid() {
        return superid;
    }

    public void setSuperid(int superid) {
        this.superid = superid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    public void setOrganization(List<Organization> organization) {
        this.organization = organization;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.superid;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + Objects.hashCode(this.power);
        hash = 31 * hash + Objects.hashCode(this.organization);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Super other = (Super) obj;
        if (this.superid != other.superid) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }
        
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }
    
    
}
