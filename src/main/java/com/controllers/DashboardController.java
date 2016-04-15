package com.controllers;

import com.exeptions.UserNotFoundException;
import com.models.User;
import com.services.database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*@PreAuthorize("denyAll")*/
public class DashboardController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dashboard")
    @PreAuthorize("hasAuthority('CTRL_USER_LIST_GET')")
    public String getDashboardIndex(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "admin-index";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = 0;
        if (principal instanceof User) {
            userId = ((User) principal).getId();
        }
        User user = null;
        try {
            user = userService.getUser(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

}
