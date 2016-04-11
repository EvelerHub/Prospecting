package com.dao;

import com.exeptions.DuplicatePermissionException;
import com.exeptions.PermissionNotFoundException;
import com.models.Permission;

import java.util.List;

public interface PermissionDAO {

    public void addPermission(Permission permission) throws DuplicatePermissionException;

    public Permission getPermission(int id) throws PermissionNotFoundException;

    public Permission getPermission(String permissionName) throws PermissionNotFoundException;

    public void updatePermission(Permission permission) throws PermissionNotFoundException;

    public void deletePermission(int id) throws PermissionNotFoundException;

    public List<Permission> getPermissions();
}