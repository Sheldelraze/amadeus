package com.minh.nguyen.controller;

import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView uploadMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-upload");
        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView updateMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-update");
        return modelAndView;
    }
}
