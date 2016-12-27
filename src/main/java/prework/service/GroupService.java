package prework.service;

import prework.entities.Group;

import java.util.Set;

import org.springframework.data.domain.Page;
import prework.entities.Subject;

public interface GroupService {

    void add(String groupName, int depId);

    void deleteById(int groupId);

    void deleteSubject(int groupId, int subjectId);

    Group getById(int groupId);
    
    Page<Group> getByDepartmentId(int depId, int page);

    Page<Group> getBySubjectsTeacherId(int teacherId, int page);

    Iterable<Group> getAll();

    Set<Group> getAll(int userId);
}
