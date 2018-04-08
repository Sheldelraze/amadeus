package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.form.user.UserCreateForm;
import com.minh.nguyen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 08/04/2018
 * Purpose:
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_USER_TEXT + "')")
    @GetMapping("/create")
    public ModelAndView getCreate(UserCreateForm userCreateForm,boolean updateSuccess) {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("user/user-create");
        if (userCreateForm == null){
            userCreateForm = new UserCreateForm();
        }
        List<Integer> lstDefaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
        boolean canEditAuth = lstDefaultAuth != null;
        if (canEditAuth){
            canEditAuth = lstDefaultAuth.contains(Constants.AUTH_EDIT_AUTHORITY_ID);
        }
        modelAndView.addObject("canEditAuth",canEditAuth);
        modelAndView.addObject("userCraeteForm", userCreateForm);
        modelAndView.addObject("updateSuccess", updateSuccess);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createUser(UserCreateForm userCreateForm, BindingResult bindingResult){
        validate(userCreateForm,bindingResult);
        if (bindingResult.hasErrors()){
            return getCreate(userCreateForm,false);
        }
        UserDTO userDTO = modelMapper.map(userCreateForm,UserDTO.class);
        userService.createUser(userDTO);
        return getCreate(userCreateForm,true);
    }
}
