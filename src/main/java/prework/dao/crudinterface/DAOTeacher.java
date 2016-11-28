package prework.dao.crudinterface;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOTeacherCustom;
import prework.entities.Teacher;

public interface DAOTeacher extends JpaRepository<Teacher, Integer>, DAOTeacherCustom{

}
