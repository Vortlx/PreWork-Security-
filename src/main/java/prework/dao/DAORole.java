package prework.dao;

import org.springframework.data.repository.CrudRepository;

import prework.dao.custom.DAORoleCustom;
import prework.entities.Role;

public interface DAORole extends CrudRepository<Role, Integer>, DAORoleCustom{

    
}
