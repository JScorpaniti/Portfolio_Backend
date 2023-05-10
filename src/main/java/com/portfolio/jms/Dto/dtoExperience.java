package com.portfolio.jms.Dto;

import javax.validation.constraints.NotBlank;


public class dtoExperience {
    @NotBlank
    private String empresa;
    @NotBlank
    private String tarea;
    
    //Constructores

    public dtoExperience() {
    }

    public dtoExperience(String empresa, String tarea) {
        this.empresa = empresa;
        this.tarea = tarea;
    }
    
    //Getter y setters

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }
    
    
}
