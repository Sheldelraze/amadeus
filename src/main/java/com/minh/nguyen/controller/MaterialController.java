package com.minh.nguyen.controller;

import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.form.material.MaterialUploadForm;
import com.minh.nguyen.service.MaterialService;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
@Controller("MaterialController")
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @Autowired
    private MaterialService materialService;

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

        return null;
    }
    @GetMapping("/update")
    public ModelAndView updateMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-update");
        return modelAndView;
    }
}
