package com.portfolio.jms.Controller;

import com.portfolio.jms.Entity.Persona;
import com.portfolio.jms.Interface.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.portfolio.jms.Interface.IPersonaService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@CrossOrigin(origins = "https://portfoliojms-f30a1.web.app")
public class PersonaController {
    
    @Autowired 
    IPersonaService ipersonaService;

    @GetMapping("/personas/traer")
    public List<Persona> getPersona() {
        return ipersonaService.getPersona();
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona) {
        ipersonaService.savePersona(persona);
        return "Creado correctamente";
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "Eliminado correctamente";
    }
    
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPerona(@PathVariable Long id,
                              @RequestParam("name") String newName,
                              @RequestParam("lastname") String newLastname,
                              @RequestParam("description") String newDescription,
                              @RequestParam("img") String newImg){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setName(newName);
        persona.setLastname(newLastname);
        persona.setDescription(newDescription);
        persona.setImg(newImg);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona() {
        return ipersonaService.findPersona((long)1);
    }
}
