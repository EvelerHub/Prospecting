package com.controllers;

import com.dao.CompanyDAO;
import com.dao.MailDAO;
import com.entity.leadpage.Lead;
import com.entity.leadpage.LeadId;
import com.entity.leadpage.LeadTable;
import com.exeptions.RecordDuplicatedException;
import com.exeptions.RecordNotFoundException;
import com.exeptions.UserNotFoundException;
import com.models.Mail;
import com.models.User;
import com.services.database.UserService;
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

    @Autowired
    private CompanyDAO companyDAO;

    // TODO: 12.04.16 USER needs to be saved in Session or in Servlet Container, for example.
    @RequestMapping(method = RequestMethod.GET)
    public String getMails(Model model) {

        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        model.addAttribute("genders", genders);

        try {
            User user = getCurrentUser();
            model.addAttribute("user", user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

        return "mails";
    }

    @ResponseBody
    @RequestMapping(value = "/get_mails", method = RequestMethod.GET)
    public LeadTable getMails() {

        try {
            User user = getCurrentUser();
            List<Mail> mails = user.getMails();

            return new LeadTable(mails);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public LeadId addMails(@RequestBody Lead lead) {
        System.out.println(lead);

        try {
            User user = getCurrentUser();
            Mail mail = lead.toMailEntity();

            long companyId = companyDAO.addCompany(mail.getCompany());
            mail.getCompany().setId(companyId);
            mail.setUser(user);

            Long mailId = mailDAO.addMail(mail);
            return new LeadId(mailId);
        } catch (RecordDuplicatedException e) {
            logger.debug(e.getMessage());
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    // FIXME: 13.04.16 must be MySQL request with array of Ids for deleting
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public List<LeadId> removeMails(@RequestBody LeadId[] ids) {

        System.out.println(Arrays.toString(ids));

        List<LeadId> deletedIdList = new ArrayList<>();
        try {
            for (LeadId id : ids) {
                Mail mail = mailDAO.deleteMail(id.getId());
                companyDAO.deleteCompany(mail.getCompany().getId());
                deletedIdList.add(id);
            }
            return deletedIdList;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    private User getCurrentUser() throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            int userId = ((User) principal).getId();
            return userService.getUser(userId);
        }

        return null;
    }

    // TODO: 12.04.16 mst be other links for Mail Table
}
