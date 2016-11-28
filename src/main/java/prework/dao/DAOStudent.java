package prework.dao;

import org.springframework.data.repository.CrudRepository;

import prework.dao.custom.DAOStudentCustom;
import prework.entities.Student;

public interface DAOStudent extends CrudRepository<Student, Integer>, DAOStudentCustom{

}
