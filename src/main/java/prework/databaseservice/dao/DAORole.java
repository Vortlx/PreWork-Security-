package prework.databaseservice.dao;


import prework.data.Role;

public interface DAORole {

    void add(Role role);

    void delete(Role role);

    Role getByName(String name);
}
