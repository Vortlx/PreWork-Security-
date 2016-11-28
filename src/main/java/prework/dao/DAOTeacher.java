package prework.dao;

import org.springframework.data.repository.CrudRepository;

import prework.dao.custom.DAOTeacherCustom;
import prework.entities.Teacher;

public interface DAOTeacher extends CrudRepository<Teacher, Integer>, DAOTeacherCustom{

}
