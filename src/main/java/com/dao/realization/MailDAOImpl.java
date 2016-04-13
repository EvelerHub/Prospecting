package com.dao.realization;

import com.dao.MailDAO;
import com.exeptions.RecordNotFoundException;
import com.models.Mail;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>MySQL DAO implementation.</p>
 *
 * @author Alexander Eveler
 */
@Repository
public class MailDAOImpl implements MailDAO {

    // TODO: 12.04.16 will be work with Logger
    
    static Logger logger = LoggerFactory.getLogger(MailDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addMail(Mail mail) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long mailId = 0L;
        try {
            transaction = session.beginTransaction();

            mailId = (Long) session.save(mail);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            session.close();
        }

        return mailId;
    }

    @Override
    public Mail getMail(long id) throws RecordNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Mail mail = null;

        try {
            transaction = session.beginTransaction();

            mail = (Mail) session.get(MailDAO.class, id);

            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            if (mail == null) {
                throw new RecordNotFoundException("Mail not found");
            }
        } finally {
            session.close();
        }

        return mail;
    }

    @Override
    public void updateMail(Mail mail) throws RecordNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Mail foundMail = null;
        try {
            transaction = session.beginTransaction();

            foundMail = (Mail) session.get(Mail.class, mail.getId());
            session.update(foundMail);

            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            if (foundMail == null) {
                throw new RecordNotFoundException("Mail not found");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteMail(long id) throws RecordNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Mail foundMail = null;
        try {
            transaction = session.beginTransaction();

            foundMail = (Mail) session.get(Mail.class, id);
            session.delete(foundMail);

            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            if (foundMail == null) {
                throw new RecordNotFoundException("Mail not found");
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Mail> getMails() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Mail> mails = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Mail");
            mails = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return mails;
    }
}
