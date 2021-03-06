package prework.dao.crudinterface;

import prework.dao.DAOSubjectCustom;
import prework.entities.Subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DAOSubject extends JpaRepository<Subject, Integer>, DAOSubjectCustom{
    
    Page<Subject> findByGroupsId(int id, Pageable pageable);
}
