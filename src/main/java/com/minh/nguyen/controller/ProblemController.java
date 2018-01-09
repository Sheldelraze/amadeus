package com.minh.nguyen.controller;

import com.minh.nguyen.form.problem.*;
import com.minh.nguyen.service.ProblemService;
import com.sun.javafx.beans.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {
    public static String LAYOUT_FORM = "problemLayoutForm";
    public static String SOLUTION_FORM = "problemSolutionForm";
    public static String STATEMENT_FORM = "problemStatementForm";
    public static String TEST_FORM = "problemTestForm";
    public static String ROLE_FORM = "problemRoleForm";
    public static String CREATE_FORM = "problemCreateForm";
    public static String CREATE_VO = "problemCreateVO";

    public static String SOLUTION_VIEW = "problem/info/problem-solution";
    public static String STATEMENT_VIEW = "problem/info/problem-statement";
    public static String TEST_VIEW = "problem/info/problem-test";
    public static String ROLE_VIEW = "problem/info/problem-role";
    public static String LIST_MY_VIEW = "problem/list/problem-list-my";
    public static String LIST_ALL_VIEW = "problem/list/problem-list-all";
    public static String CREATE_VIEW = "problem/other/problem-create";
    public static int LAYTOUT_TAB = 0;
    public static int STATEMENT_TAB = 1;
    public static int SOLUTION_TAB = 2;
    public static int TEST_TAB = 3;
    public static int ROLE_TAB = 4;
    @Autowired
    private ProblemService problemService;

    @GetMapping("/my")
    public ModelAndView getFirst() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LIST_MY_VIEW);

//        modelAndView.setViewName("share/index");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getCreate(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CREATE_VIEW);
        modelAndView.addObject(CREATE_FORM, new ProblemCreateForm());

        return modelAndView;
    }

    @PostMapping("/doCreate")
    public ModelAndView doCreate(ProblemCreateForm problemCreateForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(STATEMENT_FORM);

        return modelAndView;
    }
    public ModelAndView getGeneralInfo(int pmId,ProblemLayoutForm problemLayoutForm,int viewTab){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @GetMapping("/{pmId}/statement")
    public ModelAndView getStatement(@PathVariable("pmId") int pmId){
        ProblemStatementForm problemStatementForm = new ProblemStatementForm();
        ModelAndView modelAndView = getGeneralInfo(pmId,problemStatementForm,STATEMENT_TAB);
        modelAndView.setViewName(STATEMENT_VIEW);
        modelAndView.addObject(STATEMENT_FORM, problemStatementForm);

        return modelAndView;
    }
    @GetMapping("/{pmId}/solution")
    public ModelAndView getSolution(@PathVariable("pmId") int pmId){
        ProblemSolutionForm problemSolutionForm = new ProblemSolutionForm();
        ModelAndView modelAndView = getGeneralInfo(pmId,problemSolutionForm,SOLUTION_TAB);
        modelAndView.setViewName(SOLUTION_VIEW);
        modelAndView.addObject(SOLUTION_FORM,problemSolutionForm);

        return modelAndView;
    }
    @GetMapping("/{pmId}/test")
    public ModelAndView getTest(@PathVariable("pmId") int pmId){
        ProblemTestForm problemTestForm = new ProblemTestForm();
        ModelAndView modelAndView = getGeneralInfo(pmId,problemTestForm,TEST_TAB);
        modelAndView.setViewName(TEST_VIEW);
        modelAndView.addObject(TEST_FORM,problemTestForm);

        return modelAndView;
    }
    @GetMapping("/{pmId}/role")
    public ModelAndView getRole(@PathVariable("pmId") int pmId){
        ProblemRoleForm problemRoleForm = new ProblemRoleForm();
        ModelAndView modelAndView = getGeneralInfo(pmId,problemRoleForm,ROLE_TAB);
        modelAndView.setViewName(ROLE_VIEW);
        modelAndView.addObject(ROLE_FORM,problemRoleForm);
        return modelAndView;
    }
    @PostMapping("/{pmId}/updateGeneral")
    public ModelAndView updateGeneral(@PathVariable("pmId") int pmId,@NonNull ProblemLayoutForm problemLayoutForm){
        ModelAndView modelAndView = getGeneralInfo(pmId,problemLayoutForm,LAYTOUT_TAB);

        return modelAndView;
    }
    @PostMapping("/{pmId}/updateStatement")
    public ModelAndView updateStatement(@PathVariable("pmId") int pmId,@NonNull ProblemStatementForm problemStatementForm){
        ModelAndView modelAndView = getGeneralInfo(pmId,problemStatementForm,STATEMENT_TAB);

        return modelAndView;
    }

    @PostMapping("/{pmId}/updateSolution")
    public ModelAndView updateSolution(@PathVariable("pmId") int pmId,@NonNull ProblemSolutionForm problemSolutionForm) {
        ProblemSolutionForm problemSolutionForm1 = new ProblemSolutionForm();
        ModelAndView modelAndView = getGeneralInfo(pmId,problemSolutionForm1,SOLUTION_TAB);

        problemService.tryCompile(problemSolutionForm);
        return modelAndView;
    }

    @PostMapping("/{pmId}/updateTest")
    public ModelAndView updateTest(@PathVariable("pmId") int pmId,@NonNull ProblemTestForm problemTestForm) {
        ModelAndView modelAndView = getGeneralInfo(pmId,problemTestForm,ROLE_TAB);

        return modelAndView;
    }

    @PostMapping("/{pmId}/updateRole")
    public ModelAndView updateRole(@PathVariable("pmId") int pmId,@NonNull ProblemRoleForm problemRoleForm) {
        ModelAndView modelAndView = getGeneralInfo(pmId,problemRoleForm,ROLE_TAB);

        return modelAndView;
    }
}
