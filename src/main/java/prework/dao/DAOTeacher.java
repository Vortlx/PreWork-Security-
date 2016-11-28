package prework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.custom.DAOTeacherCustom;
import prework.entities.Teacher;

public interface DAOTeacher extends JpaRepository<Teacher, Integer>, DAOTeacherCustom{

}
