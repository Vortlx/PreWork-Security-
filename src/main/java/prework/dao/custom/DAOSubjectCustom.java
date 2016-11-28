package prework.dao.custom;

import java.util.List;

import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.entities.Teacher;

public interface DAOSubjectCustom {
    
    void addGroup(int subjectsId, Group group);

    void changeName(int subjectId, String newName);

    Subject getByNameAndType(String name, SubjectType subjectType);

    List<Group> getGroups(int subjectId);

    Teacher getTeacher(int subjectId);
}
