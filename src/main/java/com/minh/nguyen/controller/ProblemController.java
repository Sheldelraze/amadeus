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
    public static final String SOLUTION_VO = "problemSolutionVO";
    public static final String ROLE_VO = "problemRoleVO";
    public static final String TEST_VO = "problemTestVO";
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
        if (null == problemForm || null == problemForm.getId()) {
            ProblemDTO problemDTO = new ProblemDTO();
            problemForm = new ProblemLayoutForm();
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
    public ModelAndView getStatement(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, ProblemStatementForm problemStatementForm,
                                     boolean updateGeneralSuccess,  boolean updateSuccess) {
        ModelAndView modelAndView = null;
        modelAndView = getGeneralInfo(pmId, problemLayoutForm, STATEMENT_TAB, updateGeneralSuccess);

        ProblemDTO problemDTO = new ProblemDTO();
        ProblemStatementVO problemStatementVO = new ProblemStatementVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemStatementVO);
        modelAndView.addObject(STATEMENT_VO, problemStatementVO);
        if (null == problemStatementForm){
            problemStatementForm = new ProblemStatementForm();
        }
        if (updateSuccess){
            problemStatementVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(STATEMENT_FORM, problemStatementForm);
        modelAndView.setViewName(STATEMENT_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/solution")
    public ModelAndView getSolution(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm,ProblemSolutionForm problemSolutionForm,
                                    boolean updateGeneralSuccess,  boolean updateSuccess) {
        ModelAndView modelAndView = getGeneralInfo(pmId, problemLayoutForm, SOLUTION_TAB, updateGeneralSuccess);

        ProblemDTO problemDTO = new ProblemDTO();
        ProblemSolutionVO problemSolutionVO = new ProblemSolutionVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemSolutionVO);
        modelAndView.addObject(SOLUTION_VO, problemSolutionVO);
        if (null != problemSolutionForm.getSourceCode() && 0 != problemSolutionForm.getSourceCode().length()){
            problemSolutionVO.setSourceCode(problemSolutionForm.getSourceCode() );
        }
        if (null == problemSolutionForm){
            problemSolutionForm = new ProblemSolutionForm();
        }
        if (updateSuccess){
            problemSolutionVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(SOLUTION_FORM, problemSolutionForm);
        modelAndView.setViewName(SOLUTION_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/test")
    public ModelAndView getTest(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, ProblemTestForm problemTestForm,
            boolean updateGeneralSuccess,  boolean updateSuccess){
        ModelAndView modelAndView = getGeneralInfo(pmId, problemLayoutForm, TEST_TAB, updateGeneralSuccess);
        ProblemDTO problemDTO = new ProblemDTO();
        ProblemTestVO problemTestVO = new ProblemTestVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemTestVO);
        modelAndView.addObject(TEST_VO, problemTestVO);
        if (null == problemTestForm){
            problemTestForm = new ProblemTestForm();
        }
        if (updateSuccess){
            problemTestVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(TEST_FORM, problemTestForm);
        modelAndView.setViewName(TEST_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/role")
    public ModelAndView getRole(@PathVariable("pmId") int pmId, ProblemLayoutForm problemLayoutForm, ProblemRoleForm problemRoleForm,
                                boolean updateGeneralSuccess,  boolean updateSuccess){
        ModelAndView modelAndView = getGeneralInfo(pmId, problemLayoutForm, ROLE_TAB, updateGeneralSuccess);
        ProblemDTO problemDTO = new ProblemDTO();
        ProblemRoleVO problemRoleVO = new ProblemRoleVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemRoleVO);
        modelAndView.addObject(ROLE_VO, problemRoleVO);
        if (null == problemRoleForm){
            problemRoleForm = new ProblemRoleForm();
        }
        if (updateSuccess){
            problemRoleVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(ROLE_FORM, problemRoleForm);
        modelAndView.setViewName(ROLE_VIEW);
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
            if (tab == 1) {
                return getStatement(pmId, problemLayoutForm,null, false,false);
            } else if (tab == 2) {
                return getSolution(pmId, problemLayoutForm,null, false,false);
            } else if (tab == 3) {
                return getTest(pmId, problemLayoutForm,null, false,false);
            } else if (tab == 4) {
                return getRole(pmId, problemLayoutForm,null, false,false);
            }

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
        if (result.hasErrors()) {
            problemLayoutForm.setId(pmId);
            if (tab == 2) {
                return getSolution(pmId, problemLayoutForm,null, false,false);
            } else if (1 == tab) {
                return getStatement(pmId, problemLayoutForm,null, false,false);
            } else if (tab == 3) {
                return getTest(pmId, problemLayoutForm,null, false,false);
            } else if (tab == 4) {
                return getRole(pmId, problemLayoutForm,null, false,false);
            }
        }
        if (tab == 1) {
            modelAndView = getStatement(pmId, problemLayoutForm,null, true,false);
        } else if (tab == 2) {
            modelAndView = getSolution(pmId, problemLayoutForm,null, true,false);
        } else if (tab == 3) {
            modelAndView = getTest(pmId, problemLayoutForm,null, true,false);
        } else if (tab == 4) {
            modelAndView = getRole(pmId, problemLayoutForm,null, true,false);
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
            return getStatement(pmId, null,problemStatementForm,false,false);
        }
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(problemStatementForm,problemDTO);
        problemDTO.setId(pmId);
        try{
            problemService.updateProblem(problemDTO);
        }catch(Exception e){
            addLogicError(result, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }

        if (result.hasErrors()) {
            problemStatementForm.setId(pmId);
            return getStatement(pmId, null,problemStatementForm, false,false);
        }
        return getStatement(pmId,null,null,false,true);
    }

    @PostMapping("/{pmId}/updateSolution")
    public ModelAndView updateSolution(@PathVariable("pmId") int pmId,
                                       @NonNull ProblemSolutionForm problemSolutionForm,
                                       BindingResult bindingResult) {
        ModelAndView modelAndView = null;
        problemSolutionForm.setScreenName("problemSolutionScreen");
        validate(problemSolutionForm, bindingResult);
        if (bindingResult.hasErrors()) {
            problemSolutionForm.setId(pmId);
            return getSolution(pmId, null,problemSolutionForm,false,false);
        }
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(problemSolutionForm,problemDTO);
        problemDTO.setId(pmId);
        try{
            problemService.updateProblem(problemDTO);
        }catch(Exception e){
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }

        if (bindingResult.hasErrors()) {
            problemSolutionForm.setId(pmId);
            return getSolution(pmId, null,problemSolutionForm, false,false);
        }
        return getSolution(pmId,null,null,false,true);
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
