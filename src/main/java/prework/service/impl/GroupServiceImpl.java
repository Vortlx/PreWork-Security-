package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAOGroup;
import prework.entities.*;
import prework.service.DepartmentService;
import prework.service.GroupService;
import prework.service.UserService;

import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DAOGroup daoGroup;

    @Transactional(rollbackFor = Exception.class)
    public void add(String groupName, int depId) {

        Department department = departmentService.getById(depId);

        Group group = new Group();
        group.setName(groupName);
        group.setDepartment(department);

        daoGroup.save(group);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int groupId) {
        daoGroup.delete(groupId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSubject(int groupId, int subjectId) {
        daoGroup.deleteSubject(groupId, subjectId);
    }

    @Transactional(readOnly = true)
    public Group getById(int groupId) {
        return daoGroup.findOne(groupId);
    }

    @Transactional(readOnly = true)
    public Iterable<Group> getAll() {
        return daoGroup.findAll();
    }

    @Transactional(readOnly = true)
    public Set<Group> getAll(int userId){
        User user = userService.getById(userId);
        Set<Group> groups;

        if (user.getDepartment() != null) {
            Department department = user.getDepartment();
            groups = department.getGroups();
        } else {
            Teacher teacher = user.getTeacher();
            groups = teacher.getSubject().getGroups();
        }

        return groups;
    }
}
