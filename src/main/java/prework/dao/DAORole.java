package prework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.custom.DAORoleCustom;
import prework.entities.Role;

public interface DAORole extends JpaRepository<Role, Integer>, DAORoleCustom{

    
}
