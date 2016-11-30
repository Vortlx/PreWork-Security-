package prework.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAORole;
import prework.entities.Role;
import prework.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private DAORole daoRole;

    @Transactional(readOnly = true)
    public Role getByName(String roleName) {
        return daoRole.getByName(roleName);
    }
}
