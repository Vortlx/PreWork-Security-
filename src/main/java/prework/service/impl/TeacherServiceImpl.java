package prework.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAOTeacher;
import prework.entities.*;
import prework.service.*;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DAOTeacher daoTeacher;
        
    private static final int COUNT_PAGES = 10; 
    
    @Transactional(rollbackFor = Exception.class)
    public void add(String name, String familyName, Subject subject, int depId) throws Exception{

        Role role = roleService.getByName("ROLE_TEACHER");
        User newUser = userService.add(name, familyName, role);

        Department department = departmentService.getById(depId);

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setFamilyName(familyName);
        teacher.setSubject(subject);
        teacher.setDepartment(department);
        teacher.setUser(newUser);

        daoTeacher.save(teacher);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(int teacherId) {
        daoTeacher.delete(teacherId);
    }

    @Transactional(readOnly = true)
    public Teacher getById(int teacherId) {
        return daoTeacher.findOne(teacherId);
    }

    public Page<Teacher> getByDepartmentId(int depId, int page) {
        
        int zeroIndexingPage = page - 1;
        return daoTeacher.findByDepartmentId(depId, new PageRequest(zeroIndexingPage, COUNT_PAGES, Sort.Direction.ASC, "name"));
    }

    @Transactional(readOnly = true)
    public List<Teacher> getTeacher(String name, String familyName) throws Exception{
        return daoTeacher.getByNameAndFamilyName(name, familyName);
    }

    @Transactional(readOnly = true)
    public List<Teacher> getByName(String name) throws Exception{
        return daoTeacher.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<Teacher> getByFamilyName(String familyName) throws Exception{
        return daoTeacher.getByFamilyName(familyName);
    }

    @Transactional(readOnly = true)
    public Iterable<Teacher> getAll() throws Exception{
        return daoTeacher.findAll();
    }
}