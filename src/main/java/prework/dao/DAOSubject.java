package prework.dao;

import prework.dao.custom.DAOSubjectCustom;
import prework.entities.Subject;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DAOSubject extends JpaRepository<Subject, Integer>, DAOSubjectCustom{
   
}
