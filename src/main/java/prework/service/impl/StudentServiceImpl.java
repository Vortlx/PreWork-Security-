package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prework.dao.DAOStudent;
import prework.entities.*;
import prework.service.GroupService;
import prework.service.RoleService;
import prework.service.StudentService;
import prework.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private DAOStudent daoStudent;

    public void add(String name, String familyName, int groupId) throws Exception{

        Role role = roleService.getByName("ROLE_STUDENT");
        User newUser = userService.add(name, familyName, role);
        Group group = groupService.getById(groupId);

        Student student = new Student();
        student.setName(name);
        student.setFamilyName(familyName);
        student.setGroup(group);
        student.setUser(newUser);

        daoStudent.add(student);
    }

    public void deleteById(int studentId) {
        Student student = getById(studentId);
        userService.deleteById(student.getUser().getId());

        daoStudent.deleteById(studentId);
    }

    public void changeGroup(int studentId, int newGroupId) throws Exception{
        daoStudent.changeGroup(studentId, newGroupId);
    }

    public Student getById(int studentId) {
        return daoStudent.getById(studentId);
    }

    public List<Student> getStudent(String name, String familyName) throws Exception{
        return daoStudent.getStudent(name, familyName);
    }

    public List<Student> getByName(String name) throws Exception{
        return daoStudent.getByName(name);
    }

    public List<Student> getByFamilyName(String familyName) throws Exception {
        return daoStudent.getByFamilyName(familyName);
    }

    public List<Student> getAll() throws Exception{
        return daoStudent.getAll();
    }

    public Set<Student> getAll(Department department){
        Set<Student> students = new HashSet<Student>();
        for (Group group : department.getGroups()) {
            students.addAll(group.getStudents());
        }

        return students;
    }
}