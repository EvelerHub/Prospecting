package com.controllers;

import com.dao.MailDAO;
import com.entity.responses.Id;
import com.entity.responses.Mails;
import com.exeptions.RecordDuplicatedException;
import com.exeptions.RecordNotFoundException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
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
    @RequestMapping(value = "/get_mails", method = RequestMethod.GET)
    public Mails getMails() {
        List<Mail> mails = mailDAO.getMails();

        return new Mails(mails);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Id getMails(@RequestBody Mail mail) {
        System.out.println(mail);

        try {
            Long id = mailDAO.addMail(mail);
            return new Id(id);
        } catch (RecordDuplicatedException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public List<Id> getMails(@RequestBody Id[] ids) {
        // FIXME: 13.04.16 must be MySQL request with array of Ids for deleting

        System.out.println(Arrays.toString(ids));

        List<Id> deletedIdList = new ArrayList<>();
        try {
            for (Id id : ids) {
                mailDAO.deleteMail(id.getId());
                deletedIdList.add(id);
            }
            return deletedIdList;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // TODO: 12.04.16 Will be other links for Mail Table
}
