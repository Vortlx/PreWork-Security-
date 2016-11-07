package prework.data;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * This class describe student.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Entity
@Table(name="students")
public class Student extends Person {

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_group")
    private Group group;

    public Student(){
        super();
    }

    public Student(int id, String name, String familyName, Group group){
        super(id, name, familyName);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((group == null) ? 0 : group.hashCode());
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
        Student other = (Student) obj;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        return true;
    }
}