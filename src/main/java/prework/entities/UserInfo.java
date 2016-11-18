package prework.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @ManyToOne()
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private Department department;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private Teacher teacher;

    @OneToOne(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}