package prework.dao.crudinterface;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOStudentCustom;
import prework.entities.Student;

public interface DAOStudent extends JpaRepository<Student, Integer>, DAOStudentCustom{

}
