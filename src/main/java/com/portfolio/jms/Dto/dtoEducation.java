/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.jms.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author jonma
 */
public class dtoEducation {
    @NotBlank
    private String school;
    @NotBlank
    private String career;
    
    
    //constructores

    public dtoEducation() {
    }

    public dtoEducation(String school, String career) {
        this.school = school;
        this.career = career;
    }
    
    //Getters y setters

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
    
    
}
