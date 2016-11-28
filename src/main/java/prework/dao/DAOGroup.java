package prework.dao;

import org.springframework.data.repository.CrudRepository;

import prework.dao.custom.DAOGroupCustom;
import prework.entities.Group;

public interface DAOGroup extends CrudRepository<Group, Integer>, DAOGroupCustom{

}
