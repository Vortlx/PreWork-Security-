package prework.dao.crudinterface;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAODepartmentCustom;
import prework.entities.Department;

public interface DAODepartment extends JpaRepository<Department, Integer>, DAODepartmentCustom{

}