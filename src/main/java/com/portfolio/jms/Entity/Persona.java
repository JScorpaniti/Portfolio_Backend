package com.portfolio.jms.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 40, message = "Campo invalido, entre 3 y 40 caracteres maximo")
    private String name;
    
    @NotNull
    @Size(min = 3, max = 40, message = "Campo invalido, entre 3 y 40 caracteres maximo")
    private String lastname;
    
    @NotNull
    @Size (min = 5, max = 300, message = "Campo invalido, entre 5 y 300 caracteres maximo")
    private String description;
            
    @NotNull
    @Size (min =5, max = 100, message = "Campo invalido, entre 5 y 100 caracteres maximo")
    private String titulo;


    @Size(min = 3, max = 50, message = "Campo invalido")
    private String img;
    
    
}
