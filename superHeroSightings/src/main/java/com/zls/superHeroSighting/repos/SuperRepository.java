/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.superHeroSighting.repos;

import com.zls.superHeroSighting.dto.Organization;
import com.zls.superHeroSighting.dto.Power;
import com.zls.superHeroSighting.dto.Super;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zshug
 */
@Repository
public interface SuperRepository extends JpaRepository<Super,Integer> {
    List<Super> findByPower(Power powerObj);
    List<Super> findByOrganization(Organization organization);
}
