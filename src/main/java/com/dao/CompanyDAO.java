package com.dao;

import com.exeptions.RecordNotFoundException;
import com.models.Company;

/**
 * @author Alexander Eveler
 */
public interface CompanyDAO {

    Long addCompany(Company company);

    Company deleteCompany(long id) throws RecordNotFoundException;
}
