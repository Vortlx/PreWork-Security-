package prework.dao.crudinterface;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOUserCustom;
import prework.entities.User;

public interface DAOUser extends JpaRepository<User, Integer>, DAOUserCustom{

}
