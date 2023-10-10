/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.repos;

import com.zls.superHeroSighting.dto.Location;
import com.zls.superHeroSighting.dto.Sighting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zshug
 */
@Repository
public interface SightingRepository extends JpaRepository<Sighting, Integer> {
    List<Sighting> findByLocation( Location location);
}
