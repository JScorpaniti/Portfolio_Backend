package com.portfolio.jms.Repository;

import com.portfolio.jms.Entity.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Integer>{
    
    public Optional<Experience> findByEmpresa(String empresa);
    public boolean existsByEmpresa (String empresa);
    
}
