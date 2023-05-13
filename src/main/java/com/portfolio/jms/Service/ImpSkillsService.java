package com.portfolio.jms.Service;

import com.portfolio.jms.Entity.Skills;
import com.portfolio.jms.Repository.ISkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ImpSkillsService {
    @Autowired
    ISkillsRepository iskillsRepository;
    
    public List<Skills> list(){
        return iskillsRepository.findAll();
    }
    
    public Optional<Skills> getOne(int id) {
        return iskillsRepository.findById(id);
    }
    
    public Optional<Skills> getByName(String name) {
        return iskillsRepository.findByName(name);
    }
    
    public void save(Skills ski) {
        iskillsRepository.save(ski);
    }
    
    public void delete(int id) {
        iskillsRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return iskillsRepository.existsById(id);
    }
    
    public boolean existsByName(String name) {
        return iskillsRepository.existsByName(name);
    }
}
