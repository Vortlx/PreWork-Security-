package prework.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prework.dao.crudinterface.DAORole;
import prework.entities.Role;
import prework.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private DAORole daoRole;

    public Role getByName(String roleName) {
        return daoRole.getByName(roleName);
    }
}
