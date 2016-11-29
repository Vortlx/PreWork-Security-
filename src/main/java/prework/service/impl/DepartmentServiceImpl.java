package prework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import prework.dao.crudinterface.DAODepartment;
import prework.entities.Department;
import prework.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DAODepartment daoDepartment;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Department getById(int depId) {
        return daoDepartment.findOne(depId);
    }
}
