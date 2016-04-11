package com.services.realization;

import com.dao.RoleDAO;
import com.exeptions.DuplicateRoleException;
import com.exeptions.RoleNotFoundException;
import com.models.Role;
import com.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public void addRole(Role role) throws DuplicateRoleException {
        roleDAO.addRole(role);
    }

    @Override
    public Role getRole(int id) throws RoleNotFoundException {
        return roleDAO.getRole(id);
    }

    @Override
    public Role getRole(String rolename) throws RoleNotFoundException {
        return roleDAO.getRole(rolename);
    }

    @Override
    public void updateRole(Role role) throws RoleNotFoundException {
        roleDAO.updateRole(role);
    }

    @Override
    public void deleteRole(int id) throws RoleNotFoundException {
        roleDAO.deleteRole(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.getRoles();
    }
}