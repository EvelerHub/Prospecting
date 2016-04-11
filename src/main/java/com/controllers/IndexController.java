package com.controllers;

import com.exeptions.DuplicateUserException;
import com.exeptions.RoleNotFoundException;
import com.models.Role;
import com.models.User;
import com.services.PlanService;
import com.services.RoleService;
import com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@Controller
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/")
    @PreAuthorize("permitAll")
    public String getMainPage() {
        return "index";
    }

    @RequestMapping(value = "users/sign-in")
    public String getLoginPage() {
        return "sign-in";
    }

    @RequestMapping(value = "users/sign-up", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-up";
    }

    @RequestMapping(value = "users/sign-up", method = RequestMethod.POST)
    public String userSave(@Valid @ModelAttribute User user, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        logger.info("save:POST:userSave");
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.toString());
            return "index";
        } else {
            try {
                Set<Role> roleSet = new HashSet<>();
                roleSet.add(roleService.getRole("USER"));
                System.out.println(roleSet.toString());
                user.setRoles(roleSet);
                user.setPlan(planService.getPlan(2));
                user.setImagePath(getDefaultAvatar());
                userService.addUser(user);
            } catch (DuplicateUserException e) {
                e.printStackTrace();
            } catch (RoleNotFoundException e) {
                e.printStackTrace();
            }
            return "redirect:/";
        }
    }

    private byte[] getDefaultAvatar() {
        String path = "src\\main\\resources\\static\\avatars" + "/" + "default.jpeg";
        File fi = new File(path);
        byte[] fileContent = new byte[0];
        try {
            fileContent = Files.readAllBytes(fi.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

}
