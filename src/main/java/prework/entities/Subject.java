package prework.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject_type")
    @Enumerated(EnumType.STRING)
    private SubjectType type;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "group_subject",
            joinColumns = @JoinColumn(name = "id_subject"),
            inverseJoinColumns = @JoinColumn(name = "id_group"))
    private Set<Group> groups;

    @OneToOne(mappedBy = "subject", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private Teacher teacher;

    public Subject() {

    }

    public Subject(String name, SubjectType type) {
        this.name = name;
        this.type = type;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void deleteGroup(Group group) {
        groups.remove(group);
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

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}