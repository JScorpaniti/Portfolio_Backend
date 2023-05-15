package com.portfolio.jms.Controller;

import com.portfolio.jms.Dto.dtoExperience;
import com.portfolio.jms.Dto.dtoSkills;
import com.portfolio.jms.Entity.Skills;
import com.portfolio.jms.Security.Controller.Mensaje;
import com.portfolio.jms.Service.ImpSkillsService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "https://portfoliojms-f30a1.web.app")
public class SkillsController {
    @Autowired
    ImpSkillsService impskillsService;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = impskillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id){
        if(!impskillsService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Skills skills = impskillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
    
    @PostMapping("/nueva")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoSki){
        if (StringUtils.isBlank(dtoSki.getName())) 
            return new ResponseEntity (new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(impskillsService.existsByName(dtoSki.getName()))
            return new ResponseEntity (new Mensaje("Ya existe esta habilidad"), HttpStatus.BAD_REQUEST);
        
        Skills skills = new Skills(dtoSki.getName(), dtoSki.getPercent());
        impskillsService.save(skills);
        
        return new ResponseEntity(new Mensaje("Habilidad agregada correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoSki){
        if(!impskillsService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        if(impskillsService.existsByName(dtoSki.getName()) && impskillsService.getByName(dtoSki.getName()).get().getId() != id)
            return new ResponseEntity(new Mensaje("La experiencia ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoSki.getName()))
            return new ResponseEntity(new Mensaje("El nombre de la habilidad es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(dtoSki.getPercent() == 0)
            return new ResponseEntity(new Mensaje("El porcentaje no puede estar en 0"), HttpStatus.BAD_REQUEST);
        
        
        Skills skills = impskillsService.getOne(id).get();
        skills.setName(dtoSki.getName());
        skills.setPercent(dtoSki.getPercent());
        
        impskillsService.save(skills);
        return new ResponseEntity(new Mensaje("Habilidad actualizada correctamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!impskillsService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        impskillsService.delete(id);
        
        return new ResponseEntity(new Mensaje("Habilidad eliminada corrrectamente"), HttpStatus.OK);
    }
}