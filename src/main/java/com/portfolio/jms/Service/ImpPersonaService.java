package com.portfolio.jms.Service;

import com.portfolio.jms.Entity.Persona;
import com.portfolio.jms.Interface.IPersonaService;
import com.portfolio.jms.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaService implements IPersonaService{
    @Autowired 
    IPersonaRepository ipersonaRepository;
    
    
    
    public List<Persona> list(){
         return ipersonaRepository.findAll();
     }
     
     public Optional<Persona> getOne(Long id){
         return ipersonaRepository.findById(id);
     }
     
     public Optional<Persona> getByNombre(String nombre){
         return ipersonaRepository.findByName(nombre);
     }
     
     public void save(Persona persona){
         ipersonaRepository.save(persona);
     }
     
     public void delete(Long id){
         ipersonaRepository.deleteById(id);
     }
     
     public boolean existsById(Long id){
         return ipersonaRepository.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return ipersonaRepository.existsByName(nombre);
     }

    @Override
    public List<Persona> getPersona() {
        return ipersonaRepository.findAll();
    }

    @Override
    public void savePersona(Persona persona) {
        ipersonaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona() {
        List<Persona> personas = ipersonaRepository.findAll();
        if (!personas.isEmpty()) {
             return personas.get(0);
        } else {
                return null;
        }
    }
}
