package com.dao.realization;

import com.dao.PlanDAO;
import com.models.Plan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanDAOImpl implements PlanDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addPlan(Plan plan) {
        getCurrentSession().save(plan);
    }

    @Override
    public void deletePlan(Plan plan) {
        if (plan.getId() > 2)
            getCurrentSession().delete(plan);
    }

    @Override
    public void updatePlan(Plan plan) {
        getCurrentSession().update(plan);
    }

    @Override
    public List<Plan> getPlans() {
        return getCurrentSession().createQuery("from Plan").list();
    }

    @Override
    public Plan getPlan(int id) {
        return (Plan) getCurrentSession().get(Plan.class, id);
    }
}
