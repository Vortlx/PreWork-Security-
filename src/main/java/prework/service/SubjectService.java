package prework.service;

import prework.entities.Subject;
import prework.entities.SubjectType;

import java.util.List;

public interface SubjectService {

    Subject add(Subject subject);

    Subject add(String name, SubjectType type);

    void addGroup(String subjectName, String type, int groupId);

    void deleteById(int subjectId);

    Iterable<Subject> getAll();

    Subject getByNameAndType(String name, SubjectType type);
}