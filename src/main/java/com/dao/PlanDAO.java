package com.dao;

import com.models.Plan;

import java.util.List;

public interface PlanDAO {

    public void addPlan(Plan plan);

    public void deletePlan(Plan plan);

    public void updatePlan(Plan plan);

    public List<Plan> getPlans();

    public Plan getPlan(int id);
}
