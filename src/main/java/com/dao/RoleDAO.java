package com.dao;

import com.exeptions.DuplicateRoleException;
import com.exeptions.RoleNotFoundException;
import com.models.Role;

import java.util.List;

public interface RoleDAO {

    public void addRole(Role role) throws DuplicateRoleException;

    public Role getRole(int id) throws RoleNotFoundException;

    public Role getRole(String roleName) throws RoleNotFoundException;

    public void updateRole(Role role) throws RoleNotFoundException;

    public void deleteRole(int id) throws RoleNotFoundException;

    public List<Role> getRoles();
}
