package com.controllers;

import com.exeptions.RoleNotFoundException;
import com.exeptions.UserNotFoundException;
import com.models.Role;
import com.models.User;
import com.services.PlanService;
import com.services.RoleService;
import com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/account")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PlanService planService;

    private User userToUpdate;

    @RequestMapping
    public String getDefaultPage() {
        Collection<GrantedAuthority> authorities = getAuthorities();
        String rolename;
        for (GrantedAuthority authority : authorities) {
            rolename = authority.getAuthority();
            if (rolename.equals("ADMIN")) {
                logger.info("Directing to home page for: [" + rolename + "]");
                return "redirect:/dashboard";
            }
            if (rolename.equals("USER")) {
                logger.info("Directing to home page for: [" + rolename + "]");
                return "redirect:/account/profile";
            }
        }
        return "index";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(Model model) {
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        model.addAttribute("genders", genders);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = 0;
        if (principal instanceof User) {
            userId = ((User) principal).getId();
        }
        User user = null;
        try {
            user = userService.getUser(userId);
            userToUpdate = user;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute User user, /*@RequestParam("name") String name,*/ @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) throws MultipartException{
        if (bindingResult.hasFieldErrors()) {
            return "redirect:/account/profile";
        }
        if (!file.isEmpty())
            user.setImagePath(handleFileUpload(file, user.getId()));
        else
            user.setImagePath(userToUpdate.getImagePath());
        try {
            user.setPlan(userToUpdate.getPlan());
            user.setRoles(userToUpdate.getRoles());
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/mails";
    }

    private Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            authorities = ((User) principal).getAuthorities();
        } else {
            logger.error("Principal is not an instance of com.ua.model.User");
        }
        return authorities;
    }

    private byte[] handleFileUpload(MultipartFile file, int userId) {
        String name = String.valueOf(userId) + ".jpeg";
        String path = "src\\main\\resources\\static\\avatars" + "/" + name;
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(path)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
            }
            catch (Exception e) {
            }
        }
        try {
            return file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/role-admin")
    public String changeToAdmin(@RequestParam(value = "id", required = true) Integer id) {
        User user = null;
        try {
            user = userService.getUser(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Set<Role> roleSet = new HashSet<>();
        try {
            roleSet.add(roleService.getRole("ADMIN"));
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        }
        user.setRoles(roleSet);
        user.setPlan(planService.getPlan(1));
        try {
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard/members";
    }

    @RequestMapping(value = "/role-user")
    public String changeToUser(@RequestParam(value = "id", required = true) Integer id) {
        User user = null;
        try {
            user = userService.getUser(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Set<Role> roleSet = new HashSet<>();
        try {
            roleSet.add(roleService.getRole("USER"));
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        }
        user.setRoles(roleSet);
        user.setPlan(planService.getPlan(2));
        try {
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard/members";
    }

    @RequestMapping(value = "/billing")
    public String getBillingPage(Model model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("plan", getCurrentUser().getPlan());
        return "billing";
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

    @RequestMapping(value = "/forgot-password")
    public String getForgotPasswordPage() {
        return "forgot-password";
    }

    @RequestMapping (value="/qr/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getQRImage(@PathVariable final String id) {
        byte[] bytes = getCurrentUser().getImagePath();

        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED);
    }
}
