package prework.entities;


import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;


/**
 * This class describe groups in university
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 */
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Student> students;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Subject> subjects;

    @ManyToOne()
    @JoinColumn(name = "id_department")
    private Department department;

    public Group() {

    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
        students = new HashSet<Student>();
        subjects = new HashSet<Subject>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void deleteSubject(Subject subject) {
        subjects.remove(subject);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(HashSet<Student> students) {
        this.students = students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}