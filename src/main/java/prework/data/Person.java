package prework.data;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * This abstract class describe a person. For example student or teacher.
 *
 * @author Lebedev Alexander
 * @since 2016-09-19
 * */
@MappedSuperclass
public abstract class Person {
	
    @Id
    @Column(name="id")
	private int id;
    
    @Column(name="name")
	private String name;
    
    @Column(name="family_name")
	private String familyName;
	
	public Person(){
		
	}
	
	public Person(int id, String name, String familyName){
		this.id = id;
		this.name = name;
		this.familyName = familyName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((familyName == null) ? 0 : familyName.hashCode());
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
        Person other = (Person) obj;
        if (familyName == null) {
            if (other.familyName != null)
                return false;
        } else if (!familyName.equals(other.familyName))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}