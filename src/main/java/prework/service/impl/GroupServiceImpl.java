package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prework.dao.DAOGroup;
import prework.entities.*;
import prework.service.DepartmentService;
import prework.service.GroupService;
import prework.service.UserService;

import java.util.List;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DAOGroup daoGroup;

    public void add(String groupName, int depId) {

        Department department = departmentService.getById(depId);

        Group group = new Group();
        group.setName(groupName);
        group.setDepartment(department);

        daoGroup.add(group);
    }

    public void deleteById(int groupId) {

        Group group = getById(groupId);
        for (Student student : group.getStudents()) {
            userService.deleteById(student.getUser().getId());
        }
        daoGroup.deleteByID(groupId);
    }

    public void deleteSubject(int groupId, int subjectId) {
        daoGroup.deleteSubject(groupId, subjectId);
    }

    public Group getById(int groupId) {
        return daoGroup.getById(groupId);
    }

    public List<Group> getAll() {
        return daoGroup.getAll();
    }

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
