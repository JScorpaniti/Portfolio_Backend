/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.jms.Repository;

import com.portfolio.jms.Entity.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonma
 */

@Repository
public interface IEducationRepository extends JpaRepository<Education, Integer>{
    public Optional<Education> findBySchool(String school);
    public boolean existsBySchool (String school);
    
}