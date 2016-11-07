package prework.data;


import java.util.Set;
import java.util.Arrays;

import javax.persistence.*;


/**
 * This class describe teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@Entity
@Table(name="teachers")
public class Teacher extends Person {

    @ManyToMany(fetch=FetchType.EAGER, mappedBy="teachers")
    private Set<Group> groups;

    public Teacher(){
        super();
    }

    public Teacher(int id, String name, String familyName, Group... groups){
        super(id, name, familyName);
        this.groups.addAll(Arrays.asList(groups));
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group newGroup){
        groups.add(newGroup);
    }
    
    public void deleteGroup(Group group){
        groups.remove(group);
    }

    public void setGroup(Set<Group> newGroups){
        groups = newGroups;
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
        Teacher other = (Teacher) obj;
        if (groups == null) {
            if (other.groups != null)
                return false;
        } else if (!groups.equals(other.groups))
            return false;
        return true;
    }
}