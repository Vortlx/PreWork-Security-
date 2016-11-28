package prework.dao;

import prework.dao.custom.DAOSubjectCustom;
import prework.entities.Subject;
import org.springframework.data.repository.CrudRepository;


public interface DAOSubject extends CrudRepository<Subject, Integer>, DAOSubjectCustom{
   
}
