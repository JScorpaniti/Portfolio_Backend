package com.portfolio.jms.Interface;
import com.portfolio.jms.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    
    //Get Persona list
    public List<Persona> getPersona();
    
    //Save Persona object
    public void savePersona(Persona persona);
    
    //Delete object by ID
    public void deletePersona(Long id);
    
    //Find Persona by ID
    public Persona findPersona(Long id);
}
