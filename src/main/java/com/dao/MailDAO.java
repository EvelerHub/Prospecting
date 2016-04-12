package com.dao;

import com.exeptions.RecordDuplicatedException;
import com.exeptions.RecordNotFoundException;
import com.models.Mail;

import java.util.List;

/**
 * @author Alexander Eveler
 */
public interface MailDAO {

    int addMail(Mail mail) throws RecordDuplicatedException;

    Mail getMail(int id) throws RecordNotFoundException;

    void updateMail(Mail mail) throws RecordNotFoundException;

    void deleteMail(int id) throws RecordNotFoundException;

    List<Mail> getMails();
}
