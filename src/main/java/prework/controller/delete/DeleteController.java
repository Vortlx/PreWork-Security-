package prework.controller.delete;

import org.springframework.security.access.prepost.PreAuthorize;
import prework.entities.Group;
import prework.entities.Student;
import prework.entities.Teacher;
import prework.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "jsp")
public class DeleteController {

    @Autowired
    private DAOGroup daoGroup;

    @Autowired
    private DAOStudent daoStudent;

    @Autowired
    private DAOTeacher daoTeacher;

    @Autowired
    private DAOUser daoUser;

    @Autowired
    private DAOSubject daoSubject;

    @RequestMapping(value = "DeleteStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteStudent(@RequestParam("studentId") int studentId, Model model) {

        try {
            Student student = daoStudent.getById(studentId);

            daoUser.deleteByID(student.getUser().getId());
            daoStudent.deleteByID(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Students.jsp";
        }
    }

    @RequestMapping(value = "DeleteGroup", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteGroup(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId,
                              Model model) {

        try {
            Group group = daoGroup.getByID(groupId);
            for (Student student : group.getStudents()) {
                daoUser.deleteByID(student.getUser().getId());
            }

            daoGroup.deleteByID(groupId);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return "Groups";
        }
    }

    @RequestMapping(value = "DeleteTeacher", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_DEPARTMENT')")
    public String deleteTeacher(@RequestParam("teacherId") int teacherId, Model model) {

        try {
            Teacher teacher = daoTeacher.getById(teacherId);

            daoUser.deleteByID(teacher.getUser().getId());
            daoSubject.delete(teacher.getSubject().getId());
            daoTeacher.deleteByID(teacher.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "search/Teachers.jsp";
        }
    }

    @RequestMapping(value = "DeleteSubject", method = RequestMethod.GET)
    public String deleteSubjectFromGroup(@RequestParam("groupId") int groupId,
                                         @RequestParam("subjectId") int subjectId,
                                         @RequestParam("userId") int userId,
                                         Model model) {
        try {
            daoGroup.deleteSubject(groupId, subjectId);

            model.addAttribute("groupId", groupId);
            model.addAttribute("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "MySubjects";
    }
}