package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.form.user.UserCreateForm;
import com.minh.nguyen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user-create");
        if (userCreateForm == null){
            userCreateForm = new UserCreateForm();
        }
        List<Integer> lstDefaultAuthOfCurrentUser = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
        boolean canEditAuth = lstDefaultAuthOfCurrentUser != null &&  lstDefaultAuthOfCurrentUser.contains(Constants.AUTH_EDIT_AUTHORITY_ID);
        modelAndView.addObject("lstDefaultAuth",Constants.LST_DEFAULT_AUTHORITY);
        modelAndView.addObject("canEditAuth",canEditAuth);
        modelAndView.addObject("userCreateForm", userCreateForm);
        modelAndView.addObject("updateSuccess", updateSuccess);
        return modelAndView;
    }

    @GetMapping("/getDefaultAuth/{reId}")
    public ResponseEntity<?> getUserInConversation(@PathVariable("reId") Integer reId) {
        try {
            List<AuthorityDTO> lstDefaultAuth = userService.getDefaultAuthority(reId);
            return ResponseEntity.ok(lstDefaultAuth);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ModelAndView createUser(UserCreateForm userCreateForm, BindingResult bindingResult){
        validate(userCreateForm,bindingResult);
        if (bindingResult.hasErrors()){
            return getCreate(userCreateForm,false);
        }
        UserDTO userDTO = modelMapper.map(userCreateForm,UserDTO.class);
        if (userCreateForm.getLstAuyId() != null){
            userDTO.setLstAuyId(userCreateForm.getLstAuyId());
        }
        userService.createUser(userDTO);
        return getCreate(userCreateForm,true);
    }
}
