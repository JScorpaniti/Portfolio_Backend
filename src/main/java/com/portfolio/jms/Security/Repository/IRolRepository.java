
package com.portfolio.jms.Security.Repository;

import com.portfolio.jms.Security.Entity.Rol;
import com.portfolio.jms.Security.Enums.RolNombre;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
