package prework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.custom.DAOStudentCustom;
import prework.entities.Student;

public interface DAOStudent extends JpaRepository<Student, Integer>, DAOStudentCustom{

}
