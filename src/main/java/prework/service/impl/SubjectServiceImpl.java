package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAOSubject;
import prework.entities.Group;
import prework.entities.Subject;
import prework.entities.SubjectType;
import prework.service.GroupService;
import prework.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private DAOSubject daoSubject;

    @Autowired
    private GroupService groupService;
    
    private static final int COUNT_PAGES = 10;

    @Transactional(rollbackFor = Exception.class)
    public Subject add(Subject subject) {
        daoSubject.save(subject);
        return daoSubject.getByNameAndType(subject.getName(), subject.getType());
    }

    @Transactional(rollbackFor = Exception.class)
    public Subject add(String name, SubjectType type){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setType(type);

        daoSubject.save(subject);
        return daoSubject.getByNameAndType(subject.getName(), subject.getType());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addGroup(String subjectName, String type, int groupId) {
        Group group = groupService.getById(groupId);
        Subject subject = getByNameAndType(subjectName, SubjectType.valueOf(type));
        daoSubject.addGroup(subject.getId(), group);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int subjectId) {
        daoSubject.delete(subjectId);
    }

    @Transactional(readOnly = true)
    public Iterable<Subject> getAll() {
        return daoSubject.findAll();
    }

    @Transactional(readOnly = true)
    public Subject getByNameAndType(String name, SubjectType type) {
        return daoSubject.getByNameAndType(name, type);
    }

    public Page<Subject> getByGroupsId(int id, int page) {
        return daoSubject.findByGroupsId(id, new PageRequest(page - 1, COUNT_PAGES, Sort.Direction.ASC, "name"));
    }
}