package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAOStudent;
import prework.entities.*;
import prework.service.GroupService;
import prework.service.RoleService;
import prework.service.StudentService;
import prework.service.UserService;

import java.util.List;

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
    
    private static final int COUNT_PAGES = 10; 

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
    public Page<Student> getByGroupDepartmentId(int depId, int page){
        
        int zeroIndexingPage = page - 1;
        return daoStudent.findByGroupDepartmentId(depId, new PageRequest(zeroIndexingPage, COUNT_PAGES, Sort.Direction.ASC, "name"));
    }

    public Page<Student> getByGroupId(int groupId, int page) {
        
        int zeroIndexingPage = page - 1;
        return daoStudent.findByGroupId(groupId, new PageRequest(zeroIndexingPage, COUNT_PAGES, Sort.Direction.ASC, "name"));
    }
}