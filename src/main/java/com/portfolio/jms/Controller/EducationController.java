package com.portfolio.jms.Controller;

import com.portfolio.jms.Dto.dtoEducation;
import com.portfolio.jms.Entity.Education;
import com.portfolio.jms.Security.Controller.Mensaje;
import com.portfolio.jms.Service.ImpEducationService;
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

/**
 *
 * @author jonma
 */
@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://portfoliojms-f30a1.web.app")
public class EducationController {
    @Autowired
    ImpEducationService impEducationService;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = impEducationService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id){
        if(!impEducationService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Education Education = impEducationService.getOne(id).get();
        return new ResponseEntity(Education, HttpStatus.OK);
    }
    
    @PostMapping("/nueva")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoExp){
        if (StringUtils.isBlank(dtoExp.getSchool())) 
            return new ResponseEntity (new Mensaje("Este campo es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(impEducationService.existsBySchool(dtoExp.getSchool()))
            return new ResponseEntity (new Mensaje("Ya existe esta educacion"), HttpStatus.BAD_REQUEST);
        
        Education education = new Education(dtoExp.getSchool(), dtoExp.getCareer());
        impEducationService.save(education);
        
        return new ResponseEntity(new Mensaje("Educacion agregada correctamente"), HttpStatus.OK);
    }
    
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoEdu){
        if(!impEducationService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        if(impEducationService.existsBySchool(dtoEdu.getSchool()) && impEducationService.getBySchool(dtoEdu.getSchool()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esta educacion ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoEdu.getSchool()))
            return new ResponseEntity(new Mensaje("El nombre de la institucion es obligatorio"), HttpStatus.BAD_REQUEST);
        
        
        Education experiencia = impEducationService.getOne(id).get();
        experiencia.setSchool(dtoEdu.getSchool());
        experiencia.setCareer(dtoEdu.getCareer());
        
        impEducationService.save(experiencia);
        return new ResponseEntity(new Mensaje("Educacion actualizada correctamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!impEducationService.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        impEducationService.delete(id);
        
        return new ResponseEntity(new Mensaje("Educacion eliminada corrrectamente"), HttpStatus.OK);
    }
 }