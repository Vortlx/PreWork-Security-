package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prework.dao.DAOSubject;
import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.service.GroupService;
import prework.service.SubjectService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private DAOSubject daoSubject;

    @Autowired
    private GroupService groupService;

    public Subject add(Subject subject) {
        daoSubject.add(subject);
        return daoSubject.getByNameAndType(subject.getName(), subject.getType());
    }

    public Subject add(String name, SubjectType type){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setType(type);

        daoSubject.add(subject);
        return daoSubject.getByNameAndType(subject.getName(), subject.getType());
    }

    public void addGroup(String subjectName, String type, int groupId) {
        Group group = groupService.getById(groupId);
        Subject subject = getByNameAndType(subjectName, SubjectType.valueOf(type));
        daoSubject.addGroup(subject.getId(), group);
    }

    public void deleteById(int subjectId) {
        daoSubject.deleteById(subjectId);
    }

    public List<Subject> getAll() {
        return daoSubject.getAll();
    }

    public Subject getByNameAndType(String name, SubjectType type) {
        return daoSubject.getByNameAndType(name, type);
    }
}