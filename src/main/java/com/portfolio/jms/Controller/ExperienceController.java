package com.portfolio.jms.Controller;

import com.portfolio.jms.Security.Controller.Mensaje;
import com.portfolio.jms.Dto.dtoExperience;
import com.portfolio.jms.Entity.Experience;
import com.portfolio.jms.Service.ImpExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienceController {
    @Autowired
    ImpExperienceService impExperienceService;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experience>> list(){
        List<Experience> list = impExperienceService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/nueva")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoExp){
        if (StringUtils.isBlank(dtoExp.getEmpresa())) 
            return new ResponseEntity (new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(impExperienceService.existsByEmpresa(dtoExp.getEmpresa()))
            return new ResponseEntity (new Mensaje("Ya existe esta experiencia"), HttpStatus.BAD_REQUEST);
        
        Experience experiencia = new Experience(dtoExp.getEmpresa(), dtoExp.getTarea());
        impExperienceService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoExp){
        if(!impExperienceService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        if(impExperienceService.existsByEmpresa(dtoExp.getEmpresa()) && impExperienceService.getByEmpresa(dtoExp.getEmpresa()).get().getId() != id)
            return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoExp.getEmpresa()))
            return new ResponseEntity(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        
        
        Experience experiencia = impExperienceService.getOne(id).get();
        experiencia.setEmpresa(dtoExp.getEmpresa());
        experiencia.setTarea(dtoExp.getTarea());
        
        impExperienceService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada correctamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!impExperienceService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        impExperienceService.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada corrrectamente"), HttpStatus.OK);
    }
 }
   
    
