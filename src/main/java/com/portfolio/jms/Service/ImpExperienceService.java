package com.portfolio.jms.Service;

import com.portfolio.jms.Entity.Experience;
import com.portfolio.jms.Repository.IExperienceRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@Transactional
public class ImpExperienceService {
    @Autowired IExperienceRepository iexperienceRepository;
    
    
    
    public List<Experience> list() {
        return iexperienceRepository.findAll();
    }
    
    public Optional<Experience> getOne(int id) {
        return iexperienceRepository.findById(id);
    }
    
    public Optional<Experience> getByEmpresa(String empresa) {
        return iexperienceRepository.findByEmpresa(empresa);
    }
    
    public void save(Experience exp) {
        iexperienceRepository.save(exp);
    }
    
    public void delete(int id) {
        iexperienceRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return iexperienceRepository.existsById(id);
    }
    
    public boolean existsByEmpresa(String empresa) {
        return iexperienceRepository.existsByEmpresa(empresa);
    }
}


