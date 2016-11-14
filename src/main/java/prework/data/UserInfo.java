package prework.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_info")
public class UserInfo {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="usetname")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Column(name="enabled")
    private int enabled;
    
    @ManyToOne()
    @JoinColumn(name="id_role")
    private Role role;
    
    @OneToMany(mappedBy="userInfo")
    private Set<Department> department;
    
    @OneToMany(mappedBy="userInfo")
    private Set<Teacher> teacher;
    
    @OneToMany(mappedBy="userInfo")
    private Set<Student> student;

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

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }

    public Set<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(Set<Teacher> teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }
}
