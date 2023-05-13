package com.portfolio.jms.Service;

import com.portfolio.jms.Entity.Education;
import com.portfolio.jms.Repository.IEducationRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducationService {
    @Autowired
    IEducationRepository ieducationRepository;
    
    public List<Education> list(){
        return ieducationRepository.findAll();
    }
    
    public Optional<Education> getOne(int id) {
        return ieducationRepository.findById(id);
    }
    
    public Optional<Education> getBySchool(String school) {
        return ieducationRepository.findBySchool(school);
    }
    
    public void save(Education edu) {
        ieducationRepository.save(edu);
    }
    
    public void delete(int id) {
        ieducationRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return ieducationRepository.existsById(id);
    }
    
    public boolean existsBySchool(String school) {
        return ieducationRepository.existsBySchool(school);
    }
}
    

