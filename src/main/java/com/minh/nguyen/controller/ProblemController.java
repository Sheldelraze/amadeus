package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.LanguageEntity;
import com.minh.nguyen.form.problem.*;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.vo.problem.*;
import com.sun.javafx.beans.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.RollbackException;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Controller
@RequestMapping("/problem/info/")
public class ProblemController extends BaseController {
    private static final String PROBLEM_LIST = "problemList";
    private static final String LAYOUT_FORM = "problemLayoutForm";
    private static final String SOLUTION_FORM = "problemSolutionForm";
    private static final String STATEMENT_FORM = "problemStatementForm";
    private static final String ROLE_FORM = "problemRoleForm";
    private static final String CREATE_FORM = "problemCreateForm";
    private static final String CREATE_TEST_FORM = "problemCreateTestForm";
    private static final String UPDATE_TEST_FORM = "problemUpdateTestForm";
    private static final String PREVIEW_VO = "problemPreviewVO";
    private static final String LAYOUT_VO = "problemLayoutVO";
    private static final String STATEMENT_VO = "problemStatementVO";
    private static final String SOLUTION_VO = "problemSolutionVO";
    private static final String ROLE_VO = "problemRoleVO";
    private static final String TEST_VO = "problemTestVO";
    private static final String PREVIEW_VIEW = "problem/other/problem-preview";
    private static final String SOLUTION_VIEW = "problem/info/problem-solution";
    private static final String STATEMENT_VIEW = "problem/info/problem-statement";
    private static final String TEST_VIEW = "problem/info/problem-test";
    private static final String ROLE_VIEW = "problem/info/problem-role";
    private static final String VIEW = "problem/all/problem-view";
    private static final String SUBMIT_VIEW = "problem/all/problem-submit";
    private static final String UPDATE_TEST_VIEW = "problem/other/problem-update-test";
    private static final String CREATE_TEST_VIEW = "problem/other/problem-create-test";
    private static final String LIST_MY_VIEW = "problem/list/problem-list-my";
    private static final String LIST_ALL_VIEW = "problem/list/problem-list-all";
    private static final String CREATE_VIEW = "problem/other/problem-create";
    private static final String UPDATE_SUCCESS = "updateSuccess";
    private static final int STATEMENT_TAB = 1;
    private static final int SOLUTION_TAB = 2;
    private static final int TEST_TAB = 3;
    private static final int ROLE_TAB = 4;
    private static final String TAB = "tab";
    @Autowired
    private ProblemService problemService;


    @GetMapping("/my")
    public ModelAndView getMy() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LIST_MY_VIEW);
        List<ProblemDTO> lstProblem = problemService.getAllProblem();
        modelAndView.addObject(PROBLEM_LIST,lstProblem);
        return modelAndView;
    }
    @GetMapping("/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LIST_ALL_VIEW);

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

    private ModelAndView getGeneralInfo(Integer pmId, ProblemLayoutForm problemForm, int viewTab, boolean updateSuccess) {
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
    @GetMapping("/{pmId}/view")
    public ModelAndView getView(@PathVariable("pmId") Integer pmId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        problemService.getShowInStatementTest(problemDTO);
        ProblemPreviewVO problemPreviewVO = new ProblemPreviewVO();
        modelMapper.map(problemDTO,problemPreviewVO);
        problemPreviewVO.setLstInput(problemDTO.getLstInput());
        modelAndView.addObject(PREVIEW_VO,problemPreviewVO);
        modelAndView.addObject(TAB,1);
        modelAndView.addObject("pmId",pmId);
        return modelAndView;
    }
    @GetMapping("/{pmId}/submit")
    public ModelAndView getSubmit(@PathVariable("pmId") Integer pmId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUBMIT_VIEW);

        modelAndView.addObject(TAB,2);
        modelAndView.addObject("problemSubmitForm", new ProblemSubmitForm());
        List<LanguageDTO> lstLanguage = problemService.getAllLanguage();
        modelAndView.addObject("lstLanguage",lstLanguage);
        modelAndView.addObject("pmId",pmId);
        return modelAndView;
    }
    @GetMapping("/{pmId}/preview")
    public ModelAndView getPreview(@PathVariable("pmId") Integer pmId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PREVIEW_VIEW);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        problemService.getShowInStatementTest(problemDTO);
        ProblemPreviewVO problemPreviewVO = new ProblemPreviewVO();
        modelMapper.map(problemDTO,problemPreviewVO);
        problemPreviewVO.setLstInput(problemDTO.getLstInput());
        modelAndView.addObject(PREVIEW_VO,problemPreviewVO);
        return modelAndView;
    }
    @PostMapping("/{pmId}/doSubmit")
    public ModelAndView doSubmit(@PathVariable("pmId") Integer pmId, ProblemSubmitForm problemSubmitForm) {
        problemService.tryJudge(pmId,problemSubmitForm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/status");
        return modelAndView;
    }
    @GetMapping("/{pmId}/statement")
    public ModelAndView getStatement(@PathVariable("pmId") Integer pmId, ProblemLayoutForm problemLayoutForm, ProblemStatementForm problemStatementForm,
                                     boolean updateGeneralSuccess, boolean updateSuccess) {
        ModelAndView modelAndView = null;
        modelAndView = getGeneralInfo(pmId, problemLayoutForm, STATEMENT_TAB, updateGeneralSuccess);

        ProblemDTO problemDTO = new ProblemDTO();
        ProblemStatementVO problemStatementVO = new ProblemStatementVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemStatementVO);
        modelAndView.addObject(STATEMENT_VO, problemStatementVO);
        if (null == problemStatementForm) {
            problemStatementForm = new ProblemStatementForm();
        }
        if (updateSuccess) {
            problemStatementVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(STATEMENT_FORM, problemStatementForm);
        modelAndView.setViewName(STATEMENT_VIEW);
        return modelAndView;
    }
    @GetMapping("/{pmId}/solution")
    public ModelAndView getSolution(@PathVariable("pmId") Integer pmId, ProblemLayoutForm problemLayoutForm,
                                    ProblemSolutionForm problemSolutionForm,
                                    boolean updateGeneralSuccess, boolean updateSuccess) {
        ModelAndView modelAndView = getGeneralInfo(pmId, problemLayoutForm, SOLUTION_TAB, updateGeneralSuccess);

        ProblemDTO problemDTO = new ProblemDTO();
        ProblemSolutionVO problemSolutionVO = new ProblemSolutionVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemSolutionVO);
        problemSolutionVO.setLstLanguage(problemService.getAllLanguage());
        modelAndView.addObject(SOLUTION_VO, problemSolutionVO);
        if (null != problemSolutionForm
                && null != problemSolutionForm.getSourceCode()
                && 0 != problemSolutionForm.getSourceCode().length()) {
            problemSolutionVO.setSourceCode(problemSolutionForm.getSourceCode());
        }
        if (null == problemSolutionForm) {
            problemSolutionForm = new ProblemSolutionForm();
        }
        if (updateSuccess) {
            problemSolutionVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(SOLUTION_FORM, problemSolutionForm);
        modelAndView.setViewName(SOLUTION_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/test")
    public ModelAndView getTest(@PathVariable("pmId") Integer pmId, ProblemLayoutForm problemLayoutForm,
                                boolean updateGeneralSuccess, boolean updateSuccess) {
        ModelAndView modelAndView = getGeneralInfo(pmId, problemLayoutForm, TEST_TAB, updateGeneralSuccess);
        ProblemDTO problemDTO = new ProblemDTO();
        ProblemTestVO problemTestVO = new ProblemTestVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemTestVO);
        problemService.getAllTest(problemTestVO);
        if (updateSuccess) {
            problemTestVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(TEST_VO, problemTestVO);
        modelAndView.setViewName(TEST_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/updateTest/{itId}")
    public ModelAndView initUpdateTest(@PathVariable("pmId") Integer pmId, @PathVariable("itId") int itId,
                                   ProblemUpdateTestForm problemUpdateTestForm,
                                   boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        if (null == problemUpdateTestForm || null == problemUpdateTestForm.getId()) {
            problemUpdateTestForm = new ProblemUpdateTestForm();
        }
        problemUpdateTestForm.setId(itId);
        problemService.getTestCase(problemUpdateTestForm);
        modelAndView.addObject(UPDATE_TEST_FORM, problemUpdateTestForm);
        modelAndView.addObject("pmId",pmId);
        modelAndView.setViewName(UPDATE_TEST_VIEW);
        if (updateSuccess) {
            modelAndView.addObject(UPDATE_SUCCESS, true);
        }
        return modelAndView;
    }
    @PostMapping("/{pmId}/doUpdateTest/{itId}")
    public ModelAndView doUpdateTest(@PathVariable("pmId") Integer pmId,@PathVariable("itId") int itId,
                                     ProblemUpdateTestForm problemUpdateTestForm,
                                     BindingResult bindingResult){
        validate(problemUpdateTestForm,bindingResult);
        if (bindingResult.hasErrors()){
            return initUpdateTest(pmId,itId,problemUpdateTestForm,false);
        }
        try{
            ProblemDTO problemDTO = new ProblemDTO();
            problemDTO.setId(pmId);
            problemService.getProblemInfo(problemDTO);
            problemUpdateTestForm.setCode(problemDTO.getCode());
            problemUpdateTestForm.setId(itId);
            problemService.updateTest(problemUpdateTestForm);
        }catch(Exception e){
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }
        if (bindingResult.hasErrors()){
            return initUpdateTest(pmId,itId,problemUpdateTestForm,false);
        }
        return initUpdateTest(pmId,itId,problemUpdateTestForm,true);
    }
    @GetMapping("/{pmId}/deleteTest/{itId}")
    public ModelAndView deleteTest(@PathVariable("pmId") Integer pmId,@PathVariable("itId") int itId) {
        problemService.deleteTest(itId);
        return getTest(pmId,null,false,true);
    }
    @GetMapping("/{pmId}/role")
    public ModelAndView getRole(@PathVariable("pmId") Integer pmId, ProblemLayoutForm problemLayoutForm, ProblemRoleForm problemRoleForm,
                                boolean updateGeneralSuccess, boolean updateSuccess) {
        ModelAndView modelAndView = getGeneralInfo(pmId, problemLayoutForm, ROLE_TAB, updateGeneralSuccess);
        ProblemDTO problemDTO = new ProblemDTO();
        ProblemRoleVO problemRoleVO = new ProblemRoleVO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        modelMapper.map(problemDTO, problemRoleVO);
        modelAndView.addObject(ROLE_VO, problemRoleVO);
        if (null == problemRoleForm) {
            problemRoleForm = new ProblemRoleForm();
        }
        if (updateSuccess) {
            problemRoleVO.setUpdateSuccess(true);
        }
        modelAndView.addObject(ROLE_FORM, problemRoleForm);
        modelAndView.setViewName(ROLE_VIEW);
        return modelAndView;
    }

    @GetMapping("/{pmId}/createTest")
    public ModelAndView createTest(@PathVariable("pmId") Integer pmId, ProblemCreateTestForm problemCreateTestForm,
                                   boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        if (null == problemCreateTestForm || null == problemCreateTestForm.getId()) {
            problemCreateTestForm = new ProblemCreateTestForm();
        }
        modelAndView.addObject(CREATE_TEST_FORM, problemCreateTestForm);
        modelAndView.setViewName(CREATE_TEST_VIEW);
        if (updateSuccess) {
            modelAndView.addObject(UPDATE_SUCCESS, true);
        }
        return modelAndView;
    }

    @PostMapping("/{pmId}/addTest")
    public ModelAndView addTest(@PathVariable("pmId") Integer pmId, ProblemCreateTestForm problemCreateTestForm,
                                BindingResult bindingResult) {
        validate(problemCreateTestForm, bindingResult);
        if (bindingResult.hasErrors()) {
            problemCreateTestForm.setId(pmId);
            return createTest(pmId, problemCreateTestForm, false);
        }
        try {
            ProblemDTO problemDTO = new ProblemDTO();
            problemDTO.setId(pmId);
            problemService.getProblemInfo(problemDTO);
            modelMapper.map(problemCreateTestForm, problemDTO);
            problemService.createTest(problemDTO);
        } catch (RollbackException ex) {
            addLogicError(bindingResult, ex.getMessage(), new Object[]{});
        } catch (Exception e) {
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }
        if (bindingResult.hasErrors()) {
            problemCreateTestForm.setId(pmId);
            return createTest(pmId, problemCreateTestForm, false);
        }
        return createTest(pmId, null, true);
    }

    @PostMapping("/{pmId}/updateGeneral/{tab}")
    public ModelAndView updateGeneral(@PathVariable("pmId") Integer pmId,
                                      @PathVariable("tab") int tab,
                                      ProblemLayoutForm problemLayoutForm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        validate(problemLayoutForm, result);
        if (result.hasErrors()) {
            problemLayoutForm.setId(pmId);
            if (tab == 1) {
                return getStatement(pmId, problemLayoutForm, null, false, false);
            } else if (tab == 2) {
                return getSolution(pmId, problemLayoutForm, null, false, false);
            } else if (tab == 3) {
                return getTest(pmId, problemLayoutForm, false, false);
            } else if (tab == 4) {
                return getRole(pmId, problemLayoutForm, null, false, false);
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
                return getSolution(pmId, problemLayoutForm, null, false, false);
            } else if (1 == tab) {
                return getStatement(pmId, problemLayoutForm, null, false, false);
            } else if (tab == 3) {
                return getTest(pmId, problemLayoutForm, false, false);
            } else if (tab == 4) {
                return getRole(pmId, problemLayoutForm, null, false, false);
            }
        }
        if (tab == 1) {
            modelAndView = getStatement(pmId, problemLayoutForm, null, true, false);
        } else if (tab == 2) {
            modelAndView = getSolution(pmId, problemLayoutForm, null, true, false);
        } else if (tab == 3) {
            modelAndView = getTest(pmId, problemLayoutForm, true, false);
        } else if (tab == 4) {
            modelAndView = getRole(pmId, problemLayoutForm, null, true, false);
        }
        return modelAndView;
    }

    @PostMapping("/{pmId}/updateStatement")
    public ModelAndView updateStatement(@PathVariable("pmId") Integer pmId,
                                        @NonNull ProblemStatementForm problemStatementForm,
                                        BindingResult result) {
        ModelAndView modelAndView = null;
        validate(problemStatementForm, result);
        if (result.hasErrors()) {
            problemStatementForm.setId(pmId);
            return getStatement(pmId, null, problemStatementForm, false, false);
        }
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(problemStatementForm, problemDTO);
        problemDTO.setId(pmId);
        try {
            problemService.updateProblem(problemDTO);
        } catch (RollbackException ex) {
            addLogicError(result, ex.getMessage(), new Object[]{});
        } catch (Exception e) {
            addLogicError(result, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }

        if (result.hasErrors()) {
            problemStatementForm.setId(pmId);
            return getStatement(pmId, null, problemStatementForm, false, false);
        }
        return getStatement(pmId, null, problemStatementForm, false, true);
    }

    @PostMapping("/{pmId}/updateSolution")
    public ModelAndView updateSolution(@PathVariable("pmId") Integer pmId,
                                       @NonNull ProblemSolutionForm problemSolutionForm,
                                       BindingResult bindingResult) {
        problemSolutionForm.setScreenName("problemSolutionScreen");
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        problemSolutionForm.setId(pmId);
        problemSolutionForm.setCode(problemDTO.getCode());
        validate(problemSolutionForm, bindingResult);
        if (bindingResult.hasErrors()) {
            problemSolutionForm.setId(pmId);
            return getSolution(pmId, null, problemSolutionForm, false, false);
        }
        modelMapper.map(problemSolutionForm, problemDTO);
        problemDTO.setId(pmId);
        try {
            problemService.updateProblem(problemDTO);
        } catch (RollbackException ex) {
            addLogicError(bindingResult, ex.getMessage(), new Object[]{});
        } catch (Exception e) {
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }

        if (bindingResult.hasErrors()) {
            problemSolutionForm.setId(pmId);
            return getSolution(pmId, null, problemSolutionForm, false, false);
        }
        return getSolution(pmId, null, problemSolutionForm, false, true);
    }

    @PostMapping("/{pmId}/updateRole")
    public ModelAndView updateRole(@PathVariable("pmId") Integer pmId, @NonNull ProblemRoleForm problemRoleForm) {
        ModelAndView modelAndView = null;

        return modelAndView;
    }
}
