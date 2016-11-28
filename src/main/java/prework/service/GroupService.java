package prework.service;

import prework.entities.Group;

import java.util.List;
import java.util.Set;

public interface GroupService {

    void add(String groupName, int depId);

    void deleteById(int groupId);

    void deleteSubject(int groupId, int subjectId);

    Group getById(int groupId);

    Iterable<Group> getAll();

    Set<Group> getAll(int userId);
}
