package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.MaterialDTO;
import com.minh.nguyen.dto.SubjectDTO;
import com.minh.nguyen.form.material.MaterialUpdateForm;
import com.minh.nguyen.form.material.MaterialUploadForm;
import com.minh.nguyen.service.MaterialService;
import com.minh.nguyen.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
@Controller("MaterialController")
@RequestMapping("/material")
public class MaterialController extends BaseController {

    private static final String MATERIAL_ID = "mlId";

    @Autowired
    private MaterialService materialService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/all")
    public ModelAndView getAllMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-list-all");
        return modelAndView;
    }

    @GetMapping("/my")
    public ModelAndView getMyMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-list-my");
        return modelAndView;
    }

    @GetMapping("/upload")
    public ModelAndView uploadMaterial(MaterialUploadForm materialUploadForm) {
        ModelAndView modelAndView = new ModelAndView();
        if (null == materialUploadForm) {
            materialUploadForm = new MaterialUploadForm();
        }
        modelAndView.addObject("materialUploadForm", new MaterialUploadForm());
        modelAndView.setViewName("material/material-upload");
        return modelAndView;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ModelAndView doUpload(MaterialUploadForm materialUploadForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        validate(materialUploadForm, bindingResult);
        int mlId = 0;
        if (bindingResult.hasErrors()) {
            return uploadMaterial(materialUploadForm);
        }
        try {
            mlId = materialService.insertMaterial(materialUploadForm.getFile());
        } catch (RollbackException e) {
            addLogicError(bindingResult, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR);
        }
        if (bindingResult.hasErrors()) {
            return uploadMaterial(materialUploadForm);
        }
        return updateMaterial(mlId, null, true);
    }

    @GetMapping("/{mlId}/update")
    public ModelAndView updateMaterial(@PathVariable("mlId") Integer mlId, MaterialUpdateForm materialUpdateForm, boolean updateSuccess) {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-update");
        if (materialUpdateForm == null || materialUpdateForm.getName() == null) {
            MaterialDTO material = materialService.getMaterialInfo(mlId);
            materialUpdateForm = modelMapper.map(material, MaterialUpdateForm.class);
        }
        List<SubjectDTO> lstSubject = subjectService.getAllSubject();
        modelAndView.addObject("materialUpdateForm", materialUpdateForm);
        modelAndView.addObject("lstSubject", lstSubject);
        modelAndView.addObject(MATERIAL_ID, mlId);
        modelAndView.addObject(UPDATE_SUCCESS, updateSuccess);
        return modelAndView;
    }

    @PostMapping("/{mlId}/update")
    public ModelAndView doUpdateMaterial(@PathVariable("mlId") Integer mlId, MaterialUpdateForm materialUpdateForm, BindingResult bindingResult) {
        ModelAndView modelAndView = createGeneralModel();
        validate(materialUpdateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return updateMaterial(mlId, materialUpdateForm, false);
        }
        try {
            MaterialDTO material = modelMapper.map(materialUpdateForm, MaterialDTO.class);
            material.setId(mlId);
            materialService.updateMaterial(material);
        } catch (RollbackException e) {
            addLogicError(bindingResult, e.getMessage());
        } catch (Exception e) {
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR);
        }
        if (bindingResult.hasErrors()) {
            return updateMaterial(mlId, materialUpdateForm, false);
        }
        return updateMaterial(mlId, materialUpdateForm, true);
    }
}
