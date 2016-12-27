package prework.service;

import prework.entities.Subject;
import prework.entities.SubjectType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectService {

    Subject add(Subject subject);

    Subject add(String name, SubjectType type);

    void addGroup(String subjectName, String type, int groupId);

    void deleteById(int subjectId);

    Iterable<Subject> getAll();

    Subject getByNameAndType(String name, SubjectType type);
    
    Page<Subject> getByGroupId(int groupId, int page);
}