package com.services.database;

import com.exeptions.DuplicateUserException;
import com.exeptions.UserNotFoundException;
import com.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public void addUser(User user) throws DuplicateUserException;

    public User getUser(int userId) throws UserNotFoundException;

    public User getUser(String username) throws UserNotFoundException;

    public void updateUser(User user) throws UserNotFoundException;

    public void deleteUser(int userId) throws UserNotFoundException;

    public List<User> getUsers();
}
