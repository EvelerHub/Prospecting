package com.controllers;

import com.exeptions.UserNotFoundException;
import com.models.Plan;
import com.models.User;
import com.services.PlanService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/billing/plans")
@PreAuthorize("hasAuthority('CTRL_USER_LIST_GET')")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getNewPlanPage(Model model) {
        List<String> currencies = new ArrayList<>();
        currencies.add("(Select One)");
        currencies.add("United States Dollar");
        List<String> intervals = new ArrayList<>();
        intervals.add("(Select One)");
        intervals.add("Day");
        intervals.add("Week");
        intervals.add("Month");
        intervals.add("Year");
        model.addAttribute("intervals", intervals);
        model.addAttribute("currencies", currencies);
        model.addAttribute("plan", new Plan());
        model.addAttribute("user", getCurrentUser());
        return "add_plan";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewPlan(@ModelAttribute Plan plan, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        planService.addPlan(plan);
        return "redirect:/dashboard/billing/plans";
    }

    @RequestMapping
    public String getPlansPage(Model model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("plans", planService.getPlans());
        return "plans";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePlan(
            @RequestParam(value = "id", required = true) Integer id) {
        if (id == 1 || id == 2) {
            return "redirect:/dashboard/billing/plans";
        }
        else {
            Plan plan = planService.getPlan(id);
            planService.deletePlan(plan);
            return "redirect:/dashboard/billing/plans";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getPlanUpdatePage(@RequestParam(value = "id", required = true) Integer id,
                                 Model model) {
        model.addAttribute("user", getCurrentUser());
        if (id == 1) {
            return "redirect:/dashboard/billing/plans";
        }
        else {
            Plan plan = planService.getPlan(id);
            System.out.println(plan.toString());
            List<String> currencies = new ArrayList<>();
            currencies.add("(Select One)");
            currencies.add("United States Dollar");
            List<String> intervals = new ArrayList<>();
            intervals.add("(Select One)");
            intervals.add("Day");
            intervals.add("Week");
            intervals.add("Month");
            intervals.add("Year");
            model.addAttribute("intervals", intervals);
            model.addAttribute("currencies", currencies);
            model.addAttribute("plan", plan);
            return "edit_plan";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute Plan plan) {
        planService.updatePlan(plan);
        return "redirect:/dashboard/billing/plans";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = 0;
        if (principal instanceof User) {
            userId = ((User) principal).getId();
        }
        User user = null;
        try {
            user = userService.getUser(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
