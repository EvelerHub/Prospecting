package com.services.database.realization;

import com.dao.PlanDAO;
import com.models.Plan;
import com.services.database.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanDAO planDAO;

    @Override
    public void addPlan(Plan plan) {
        planDAO.addPlan(plan);
    }

    @Override
    public void deletePlan(Plan plan) {
        planDAO.deletePlan(plan);
    }

    @Override
    public void updatePlan(Plan plan) {
        planDAO.updatePlan(plan);
    }

    @Override
    public List<Plan> getPlans() {
        return planDAO.getPlans();
    }

    @Override
    public Plan getPlan(int id) {
        return planDAO.getPlan(id);
    }
}
