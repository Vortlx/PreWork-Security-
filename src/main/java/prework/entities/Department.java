package prework.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Group> groups;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Teacher> teachers;

    @OneToOne()
    @JoinColumn(name = "id_user_info")
    private User user;

    public Department() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void deleteGroup(Group group) {
        groups.remove(group);
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Student> getStudents(){
        Set<Student> students = new HashSet<Student>();
        for (Group group : groups) {
            students.addAll(group.getStudents());
        }
        return students;
    }
}