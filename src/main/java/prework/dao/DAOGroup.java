package prework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.custom.DAOGroupCustom;
import prework.entities.Group;

public interface DAOGroup extends JpaRepository<Group, Integer>, DAOGroupCustom{

}
