package prework.dao.crudinterface;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAORoleCustom;
import prework.entities.Role;

public interface DAORole extends JpaRepository<Role, Integer>, DAORoleCustom{

    
}
