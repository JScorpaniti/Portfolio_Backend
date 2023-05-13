package com.portfolio.jms.Repository;

import com.portfolio.jms.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISkillsRepository extends JpaRepository<Skills, Integer>{
    
    public Optional<Skills> findByName(String name);
    public boolean existsByName (String name);
    
}
