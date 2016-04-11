package com.dao;

import com.exeptions.DuplicateUserException;
import com.exeptions.UserNotFoundException;
import com.models.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user) throws DuplicateUserException;

    public User getUser(int userId) throws UserNotFoundException;

    public User getUser(String username) throws UserNotFoundException;

    public void updateUser(User user) throws UserNotFoundException;

    public void deleteUser(int userId) throws UserNotFoundException;

    public List<User> getUsers();

}