/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.dto;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author zshug
 */
@Entity
public class Sighting {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int sightingid;
    
    @ManyToOne
    @JoinColumn(name = "superid", nullable = false)
    private Super superObj;
    
    @ManyToOne
    @JoinColumn(name = "locationid", nullable = false)
    private Location location;
    
    @Column(nullable = false)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate date;

    public int getSightingid() {
        return sightingid;
    }

    public void setSightingid(int sightingid) {
        this.sightingid = sightingid;
    }

    public Super getSuperObj() {
        return superObj;
    }

    public void setSuperObj(Super superObj) {
        this.superObj = superObj;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.sightingid;
        hash = 53 * hash + Objects.hashCode(this.superObj);
        hash = 53 * hash + Objects.hashCode(this.location);
        hash = 53 * hash + Objects.hashCode(this.date);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingid != other.sightingid) {
            return false;
        }
        if (!Objects.equals(this.superObj, other.superObj)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
}
