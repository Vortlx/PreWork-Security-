package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAOStudent;
import prework.entities.*;
import prework.service.GroupService;
import prework.service.RoleService;
import prework.service.StudentService;
import prework.service.UserService;

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

    @Transactional(rollbackFor = Exception.class)
    public void add(String name, String familyName, int groupId) throws Exception{

        Role role = roleService.getByName("ROLE_STUDENT");
        User newUser = userService.add(name, familyName, role);
        Group group = groupService.getById(groupId);

        Student student = new Student();
        student.setName(name);
        student.setFamilyName(familyName);
        student.setGroup(group);
        student.setUser(newUser);

        daoStudent.save(student);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int studentId) {
        daoStudent.delete(studentId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeGroup(int studentId, int newGroupId) throws Exception{
        daoStudent.changeGroup(studentId, newGroupId);
    }

    @Transactional(readOnly = true)
    public Student getById(int studentId) {
        return daoStudent.findOne(studentId);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudent(String name, String familyName) throws Exception{
        return daoStudent.getByNameAndFamilyName(name, familyName);
    }

    @Transactional(readOnly = true)
    public List<Student> getByName(String name) throws Exception{
        return daoStudent.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<Student> getByFamilyName(String familyName) throws Exception {
        return daoStudent.getByFamilyName(familyName);
    }

    @Transactional(readOnly = true)
    public Iterable<Student> getAll() throws Exception{
        return daoStudent.findAll();
    }

    @Transactional(readOnly = true)
    public Set<Student> getAll(Department department){
        return department.getStudents();
    }
}