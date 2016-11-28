package prework.entities;


import javax.persistence.*;


/**
 * This class describe teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 */
@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @OneToOne()
    @JoinColumn(name = "id_subject")
    private Subject subject;

    @ManyToOne()
    @JoinColumn(name = "id_department")
    private Department department;

    public Teacher() {
        super();
    }

    public Teacher(int id, String name, String familyName) {
        super(id, name, familyName);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        //result = prime * result + ((groups == null) ? 0 : groups.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }
}