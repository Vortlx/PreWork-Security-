package prework.dao;

import org.springframework.data.repository.CrudRepository;

import prework.dao.custom.DAOUserCustom;
import prework.entities.User;

public interface DAOUser extends CrudRepository<User, Integer>, DAOUserCustom{

}
