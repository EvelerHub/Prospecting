package com.controllers;

import com.dao.MailDAO;
import com.entity.MailList;
import com.exeptions.UserNotFoundException;
import com.models.Mail;
import com.models.User;
import com.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Controller of Mails page.</h3>
 * <p>Contains the functionality of Mail page.</p>
 *
 * @author Alexander Eveler
 */
@Controller
@RequestMapping(value = "/mails")
public class MailController {

    private static Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailDAO mailDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String getMails(Model model) {
        // TODO: 12.04.16 USER needs to be saved in Session or in Servlet Container, for example.
        // TODO: 12.04.16 After authorization all elements of navbar needs to be saved in Session.

        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        model.addAttribute("genders", genders);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = 0;
        if (principal instanceof User) {
            userId = ((User) principal).getId();
        }

        try {
            User user = userService.getUser(userId);
            model.addAttribute("user", user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        return "mails";
    }

    @ResponseBody
    @RequestMapping(value = "/get_mails", method =  RequestMethod.GET)
    public MailList getMails(){
        List<Mail> mails = mailDAO.getMails();

        return new MailList(mails);
    }

}
