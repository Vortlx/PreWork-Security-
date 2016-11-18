package prework.dao;


import prework.entities.Role;

public interface DAORole {

    void add(Role role);

    void delete(Role role);

    Role getByName(String name);
}
