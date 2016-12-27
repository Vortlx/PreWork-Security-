package prework.dao.crudinterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOStudentCustom;
import prework.entities.Student;

public interface DAOStudent extends JpaRepository<Student, Integer>, DAOStudentCustom{

    Page<Student> findByGroupDepartmentId(int id, Pageable pageable);
    
    Page<Student> findByGroupId(int id, Pageable pageable);
}
