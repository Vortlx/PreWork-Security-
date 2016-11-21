package prework.service;


import prework.entities.Department;
import prework.entities.Student;

import java.util.List;
import java.util.Set;


public interface StudentService {

    void add(String name, String familyName, int groupId) throws Exception;

    void deleteById(int studentId);

    void changeGroup(int studentId, int newGroupId) throws Exception;

    Student getById(int studentId);

    List<Student> getStudent(String name, String familyName) throws Exception;

    List<Student> getByName(String name) throws Exception;

    List<Student> getByFamilyName(String familyName) throws Exception;

    List<Student> getAll() throws Exception;

    Set<Student> getAll(Department department);
}
