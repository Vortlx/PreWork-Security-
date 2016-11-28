package prework.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prework.dao.DAOTeacher;
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
    private SubjectService subjectService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DAOTeacher daoTeacher;

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

    public void deleteById(int teacherId) {
        Teacher teacher = getById(teacherId);

        userService.deleteById(teacher.getUser().getId());
        subjectService.deleteById(teacher.getSubject().getId());

        daoTeacher.delete(teacherId);
    }

    public Teacher getById(int teacherId) {
        return daoTeacher.findOne(teacherId);
    }

    public List<Teacher> getTeacher(String name, String familyName) throws Exception{
        return daoTeacher.getTeacher(name, familyName);
    }

    public List<Teacher> getByName(String name) throws Exception{
        return daoTeacher.getByName(name);
    }

    public List<Teacher> getByFamilyName(String familyName) throws Exception{
        return daoTeacher.getByFamilyName(familyName);
    }

    public Iterable<Teacher> getAll() throws Exception{
        return daoTeacher.findAll();
    }
}