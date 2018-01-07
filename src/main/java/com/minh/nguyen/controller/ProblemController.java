package com.minh.nguyen.controller;

import com.minh.nguyen.form.problem.ProblemLayoutForm;
import com.minh.nguyen.form.problem.ProblemSolutionForm;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.util.FileUtil;
import com.sun.javafx.beans.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Controller
//@RequestMapping("problem")
public class ProblemController {
    public static String PROBLEM_SOLUTION_FORM = "problemSolutionForm";

    @Autowired
    private ProblemService problemService;
    @GetMapping("/")
    public ModelAndView getFirst() {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("submission/submission");
//        modelAndView.setViewName("contest/info/contest-role");
        modelAndView.setViewName("problem/info/problem-solution");
        modelAndView.addObject(PROBLEM_SOLUTION_FORM,new ProblemSolutionForm());
//        modelAndView.setViewName("share/index");
        return modelAndView;
    }

    @PostMapping("updateSol")
    public ModelAndView updateSol(@NonNull ProblemSolutionForm problemSolutionForm) {
        ModelAndView modelAndView = new ModelAndView();
        problemService.tryCompile(problemSolutionForm);
        return modelAndView;
    }
}
