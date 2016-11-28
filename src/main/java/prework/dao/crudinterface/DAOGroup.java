package prework.dao.crudinterface;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOGroupCustom;
import prework.entities.Group;

public interface DAOGroup extends JpaRepository<Group, Integer>, DAOGroupCustom{

}
