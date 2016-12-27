package prework.service;

import prework.entities.Group;

import java.util.Set;

import org.springframework.data.domain.Page;

public interface GroupService {

    void add(String groupName, int depId);

    void deleteById(int groupId);

    void deleteSubject(int groupId, int subjectId);

    Group getById(int groupId);
    
    Page<Group> getByDepartmentId(int depId, int page);

    Iterable<Group> getAll();

    Set<Group> getAll(int userId);
}
