package prework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.custom.DAODepartmentCustom;
import prework.entities.Department;

public interface DAODepartment extends JpaRepository<Department, Integer>, DAODepartmentCustom{

}