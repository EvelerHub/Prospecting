package com.dao.realization;

import com.dao.CompanyDAO;
import com.exeptions.RecordNotFoundException;
import com.models.Company;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Alexander Eveler
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {

    static Logger logger = LoggerFactory.getLogger(CompanyDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Long addCompany(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Long id = 0L;
        try {
            transaction = session.beginTransaction();
            id = (Long) session.save(company);
            transaction.commit();
            return id;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public Company deleteCompany(long id) throws RecordNotFoundException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Company foundCompany = null;
        try {
            transaction = session.beginTransaction();
            foundCompany = (Company) session.get(Company.class, id);
            session.delete(foundCompany);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            if (foundCompany == null) {
                throw new RecordNotFoundException("Mail not found");
            }
        } finally {
            session.close();
        }

        return foundCompany;
    }
}
