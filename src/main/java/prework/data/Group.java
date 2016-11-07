package prework.data;


import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * This class describe groups in university
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Entity
@Table(name="groups")
public class Group {

    @Id
    @Column(name="id")
	int id;
    
    @Column(name="name")
	private String name;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="group")
	private Set<Student> students;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="curator", 
            joinColumns={@JoinColumn(name="id_group")}, 
            inverseJoinColumns={@JoinColumn(name="id_teacher")})
	private Set<Teacher> teachers;
	
	public Group(){
		
	}
	
	public Group(int id, String name){
		this.id = id;
		this.name = name;
		students = new HashSet<Student>();
		teachers = new HashSet<Teacher>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}

	public void addStudent(Student student){
		students.add(student);
	}
	
	public void addTeacher(Teacher teacher){
	    teachers.add(teacher);
	}
	
	public void deleteTeacher(Teacher teacher){
	    teachers.remove(teacher);
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

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        //result = prime * result + ((students == null) ? 0 : students.hashCode());
        //result = prime * result + ((teachers == null) ? 0 : teachers.hashCode());
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