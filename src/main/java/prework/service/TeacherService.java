package prework.service;

import prework.entities.Subject;
import prework.entities.Teacher;

import java.util.List;

import org.springframework.data.domain.Page;

public interface TeacherService {

    void add(String name, String familyName, Subject subject, int depId) throws Exception;

    void deleteById(int teacherId);

    Teacher getById(int teacherId);
    
    Page<Teacher> gettByDepartmentId(int depId, int page);

    List<Teacher> getTeacher(String name, String familyName) throws Exception;

    List<Teacher> getByName(String name) throws Exception;

    List<Teacher> getByFamilyName(String familyName) throws Exception;

    Iterable<Teacher> getAll() throws Exception;
}
