package prework.dao.crudinterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import prework.dao.DAOGroupCustom;
import prework.entities.Group;

public interface DAOGroup extends JpaRepository<Group, Integer>, DAOGroupCustom{

    Page<Group> findByDepartmentId(int id, Pageable pageable);
}
