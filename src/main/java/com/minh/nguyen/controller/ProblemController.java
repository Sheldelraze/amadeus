package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.form.problem.*;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.vo.problem.*;
import com.sun.javafx.beans.annotations.NonNull;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import javax.persistence.RollbackException;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Controller
@RequestMapping("/problem")
public class ProblemController extends BaseController {
    public static final String LAYOUT_FORM = "problemLayoutForm";
    public static final String SOLUTION_FORM = "problemSolutionForm";
    public static final String STATEMENT_FORM = "problemStatementForm";
    public static final String TEST_FORM = "problemTestForm";
    public static final String ROLE_FORM = "problemRoleForm";
    public static final String CREATE_FORM = "problemCreateForm";
    public static final String LAYOUT_VO = "problemLayoutVO";
    public static final String CREATE_VO = "problemCreateVO";
    public static final String STATEMENT_VO = "problemStatementVO";
    public static final String NIC_EDITOR = "nicEditorIcons";
    public static final String SOLUTION_VIEW = "problem/info/problem-solution";
    public static final String STATEMENT_VIEW = "problem/info/problem-statement";
    public static final String TEST_VIEW = "problem/info/problem-test";
    public static final String ROLE_VIEW = "problem/info/problem-role";
    public static final String LIST_MY_VIEW = "problem/list/problem-list-my";
    public static final String LIST_ALL_VIEW = "problem/list/problem-list-all";
    public static final String NIC_EDITOR_PATH = "'../../assets/images/users/nicEditorIcons.gif'";
    public static final String CREATE_VIEW = "problem/other/problem-create";
    public static final String UPDATE_SUCCESS = "updateSuccess";
    public static final int LAYTOUT_TAB = 0;
    public static final int STATEMENT_TAB = 1;
    public static final int SOLUTION_TAB = 2;
    public static final int TEST_TAB = 3;
    public static final int ROLE_TAB = 4;
    public static final String TAB = "tab";
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
    public ModelAndView getCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CREATE_VIEW);
        modelAndView.addObject(CREATE_FORM, new ProblemCreateForm());
        return modelAndView;
    }

    @PostMapping("/doCreate")
    public ModelAndView doCreate(ProblemCreateForm problemCreateForm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(problemCreateForm, problemDTO);
        problemCreateForm.setScreenName("makerAdd");
        validate(problemCreateForm, result);
        if (result.hasErrors()) {
            modelAndView.setViewName(CREATE_VIEW);
            modelAndView.addObject(CREATE_FORM, problemCreateForm);
            return modelAndView;
        }
        try {
            problemService.createProblem(problemDTO);
        } catch (RollbackException ex) {
            addLogicError(result, ex.getMessage(), new Object[]{});
        } catch (Exception e) {
            addLogicError(result, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }
        if (result.hasErrors()) {
            modelAndView.setViewName(CREATE_VIEW);
            modelAndView.addObject(CREATE_FORM, problemCreateForm);
            return modelAndView;
        }
        return new ModelAndView("redirect:/problem/" + problemDTO.getId() + "/statement");
    }

    public ModelAndView getGeneralInfo(int pmId, ProblemLayoutForm problemForm, int viewTab, boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        ProblemLayoutVO problemLayoutVO = null;
        if (null == problemForm.getCode() || Constants.BLANK.equals(problemForm.getCode())) {
            if (null == problemForm || 0 == problemForm.getId()){
                if (viewTab == STATEMENT_TAB) {
                    problemForm = new ProblemStatementForm();
                } else if (viewTab == SOLUTION_TAB) {
                    problemForm = new ProblemSolutionForm();
                } else if (viewTab == TEST_TAB) {
                    problemForm = new ProblemTestForm();
                } else if (viewTab == ROLE_TAB) {
                    problemForm = new ProblemRoleForm();
                }
            }
            ProblemDTO problemDTO = new ProblemDTO();
            problemDTO.setId(pmId);
            try {
                problemService.getProblemInfo(problemDTO);
            } catch (Exception e) {
                //TO-DO: add error handle here
            }
            modelMapper.map(problemDTO, problemForm);
        }
        if (viewTab == STATEMENT_TAB) {
            problemLayoutVO = new ProblemStatementVO();
        } else if (viewTab == SOLUTION_TAB) {
            problemLayoutVO = new ProblemSolutionVO();
        } else if (viewTab == TEST_TAB) {
            problemLayoutVO = new ProblemTestVO();
        } else if (viewTab == ROLE_TAB) {
            problemLayoutVO = new ProblemRoleVO();
        }
        modelMapper.map(problemForm, problemLayoutVO);
        problemLayoutVO.setUpdateSuccess(updateSuccess);
        modelAndView.addObject(LAYOUT_VO, problemLayoutVO);
        modelAndView.addObject(LAYOUT_FORM, problemForm);
        modelAndView.addObject(TAB, viewTab);
        return modelAndView;
    }

    @GetMapping("/{pmId}/statement")
    public ModelAndView getStatement(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, ProblemStatementForm problemStatementForm, boolean updateSuccess) {
        ModelAndView modelAndView = null;
        modelAndView = getGeneralInfo(pmId, problemLayoutForm, STATEMENT_TAB, updateSuccess);

        ProblemDTO problemDTO = new ProblemDTO();
        ProblemStatementVO problemStatementVO = new ProblemStatementVO();
        problemDTO.setId(problemLayoutForm.getId());
        problemService.getStatementInfo(problemDTO);
        modelMapper.map(problemLayoutForm, problemStatementVO);
        modelAndView.addObject(STATEMENT_VO, problemStatementVO);
        if (null == problemStatementForm){
            problemStatementForm = new ProblemStatementForm();
        }
        modelAndView.addObject(STATEMENT_FORM, problemStatementForm);
        modelAndView.setViewName(STATEMENT_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/solution")
    public ModelAndView getSolution(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, boolean updateSuccess) {
        ProblemSolutionForm problemSolutionForm = new ProblemSolutionForm();
        ModelAndView modelAndView = getGeneralInfo(pmId, problemSolutionForm, SOLUTION_TAB, updateSuccess);
        modelAndView.setViewName(SOLUTION_VIEW);
        modelAndView.addObject(SOLUTION_FORM, problemSolutionForm);

        return modelAndView;
    }

    @GetMapping("/{pmId}/test")
    public ModelAndView getTest(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, boolean updateSuccess) {
        ProblemTestForm problemTestForm = new ProblemTestForm();
        ModelAndView modelAndView = getGeneralInfo(pmId, problemTestForm, TEST_TAB, updateSuccess);
        modelAndView.setViewName(TEST_VIEW);
        modelAndView.addObject(TEST_FORM, problemTestForm);

        return modelAndView;
    }

    @GetMapping("/{pmId}/role")
    public ModelAndView getRole(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, boolean updateSuccess) {
        ProblemRoleForm problemRoleForm = new ProblemRoleForm();
        ModelAndView modelAndView = getGeneralInfo(pmId, problemRoleForm, ROLE_TAB, updateSuccess);
        modelAndView.setViewName(ROLE_VIEW);
        modelAndView.addObject(ROLE_FORM, problemRoleForm);
        return modelAndView;
    }

    @PostMapping("/{pmId}/updateGeneral/{tab}")
    public ModelAndView updateGeneral(@PathVariable("pmId") int pmId,
                                      @PathVariable("tab") int tab,
                                      ProblemLayoutForm problemLayoutForm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        validate(problemLayoutForm, result);
        if (result.hasErrors()) {
            problemLayoutForm.setId(pmId);
            return getStatement(pmId, problemLayoutForm,null, false);
        }
        problemLayoutForm.setId(pmId);
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(problemLayoutForm, problemDTO);
        try {
            problemService.updateProblem(problemDTO);
        } catch (RollbackException ex) {
            addLogicError(result, ex.getMessage(), new Object[]{});
        } catch (Exception e) {
            addLogicError(result, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }
        if (tab == 1) {
            modelAndView = getStatement(pmId, problemLayoutForm,null, true);
        } else if (tab == 2) {

        } else if (tab == 3) {

        } else if (tab == 4) {

        }
        return modelAndView;
    }

    @PostMapping("/{pmId}/updateStatement")
    public ModelAndView updateStatement(@PathVariable("pmId") int pmId,
                                        @NonNull ProblemStatementForm problemStatementForm,
                                        BindingResult result) {
        ModelAndView modelAndView = null;
        validate(problemStatementForm, result);
        if (result.hasErrors()) {
            problemStatementForm.setId(pmId);
            return getStatement(pmId, null,problemStatementForm, false);
        }
        return modelAndView;
    }

    @PostMapping("/{pmId}/updateSolution")
    public ModelAndView updateSolution(@PathVariable("pmId") int pmId, @NonNull ProblemSolutionForm problemSolutionForm) {
        ProblemSolutionForm problemSolutionForm1 = new ProblemSolutionForm();
        ModelAndView modelAndView = null;

        problemService.tryCompile(problemSolutionForm);
        return modelAndView;
    }

    @PostMapping("/{pmId}/updateTest")
    public ModelAndView updateTest(@PathVariable("pmId") int pmId, @NonNull ProblemTestForm problemTestForm) {
        ModelAndView modelAndView = null;

        return modelAndView;
    }

    @PostMapping("/{pmId}/updateRole")
    public ModelAndView updateRole(@PathVariable("pmId") int pmId, @NonNull ProblemRoleForm problemRoleForm) {
        ModelAndView modelAndView = null;

        return modelAndView;
    }
}
