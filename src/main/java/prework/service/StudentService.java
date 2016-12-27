package prework.service;


import prework.entities.Student;

import java.util.List;

import org.springframework.data.domain.Page;


public interface StudentService {

    void add(String name, String familyName, int groupId) throws Exception;

    void deleteById(int studentId);

    void changeGroup(int studentId, int newGroupId) throws Exception;

    Student getById(int studentId);

    List<Student> getStudent(String name, String familyName) throws Exception;

    List<Student> getByName(String name) throws Exception;

    List<Student> getByFamilyName(String familyName) throws Exception;

    Iterable<Student> getAll() throws Exception;

    Page<Student> getByGroupDepartmentId(int depId, int page);
}