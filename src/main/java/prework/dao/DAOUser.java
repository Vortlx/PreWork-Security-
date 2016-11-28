package prework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.custom.DAOUserCustom;
import prework.entities.User;

public interface DAOUser extends JpaRepository<User, Integer>, DAOUserCustom{

}
