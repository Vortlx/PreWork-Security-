package prework.dao.crudinterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOTeacherCustom;
import prework.entities.Teacher;

public interface DAOTeacher extends JpaRepository<Teacher, Integer>, DAOTeacherCustom{
    
    Page<Teacher> findByDepartmentId(int id, Pageable pageable);
}
