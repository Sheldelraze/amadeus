package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.LecturerDTO;
import com.minh.nguyen.dto.StudentDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.ClassEntity;
import com.minh.nguyen.form.user.UserCreateForm;
import com.minh.nguyen.form.user.UserUpdateForm;
import com.minh.nguyen.form.user.UserUpdatePasswordForm;
import com.minh.nguyen.form.user.profile.LecturerProfileUpdateForm;
import com.minh.nguyen.form.user.profile.StudentProfileUpdateForm;
import com.minh.nguyen.form.user.profile.UserProfileUpdateForm;
import com.minh.nguyen.service.UserService;
import com.minh.nguyen.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 08/04/2018
 * Purpose:
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private MessageUtil messageUtil;

    @GetMapping("/all")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user-all");
        return modelAndView;
    }
    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_USER_TEXT + "')")
    @GetMapping("/create")
    public ModelAndView getCreate(UserCreateForm userCreateForm, boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user-create");
        if (userCreateForm == null) {
            userCreateForm = new UserCreateForm();
        }
        List<Integer> lstDefaultAuthOfCurrentUser = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
        boolean canEditAuth = lstDefaultAuthOfCurrentUser != null && lstDefaultAuthOfCurrentUser.contains(Constants.AUTH_EDIT_AUTHORITY_ID);
        modelAndView.addObject("lstDefaultAuth", Constants.LST_DEFAULT_AUTHORITY);
        modelAndView.addObject("canEditAuth", canEditAuth);
        modelAndView.addObject("userCreateForm", userCreateForm);
        modelAndView.addObject("updateSuccess", updateSuccess);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated() && @UserValidator.checkIfCreatorOrAdmin(authentication,#urId)")
    @GetMapping("/{urId}/update")
    public ModelAndView updateUser(@PathVariable("urId") Integer urId, UserUpdateForm userUpdateForm, boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user-update");
        if (userUpdateForm.getId() == null) {
            UserDTO userDTO = userService.getUserInformationToUpdate(urId);
            userUpdateForm = modelMapper.map(userDTO, UserUpdateForm.class);
        }
        List<Integer> lstDefaultAuthOfCurrentUser = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
        boolean canEditAuth = lstDefaultAuthOfCurrentUser != null && lstDefaultAuthOfCurrentUser.contains(Constants.AUTH_EDIT_AUTHORITY_ID);
        modelAndView.addObject("urId", urId);
        modelAndView.addObject("lstDefaultAuth", userService.getDefaultAuthoritiesForUser(urId));
        modelAndView.addObject("canEditAuth", canEditAuth);
        modelAndView.addObject("userUpdateForm", userUpdateForm);
        modelAndView.addObject("updateSuccess", updateSuccess);
        return modelAndView;
    }

    @GetMapping("/getDefaultAuth/{reId}")
    public ResponseEntity<?> getDefaultAuthForRole(@PathVariable("reId") Integer reId) {
        try {
            List<AuthorityDTO> lstDefaultAuth = userService.getDefaultAuthority(reId);
            return ResponseEntity.ok(lstDefaultAuth);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ModelAndView createUser(UserCreateForm userCreateForm, BindingResult bindingResult) {
        validate(userCreateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return getCreate(userCreateForm, false);
        }
        UserDTO userDTO = modelMapper.map(userCreateForm, UserDTO.class);
        if (userCreateForm.getLstAuyId() != null) {
            userDTO.setLstAuyId(userCreateForm.getLstAuyId());
        }
        userService.createUser(userDTO);
        return getCreate(userCreateForm, true);
    }

    @PreAuthorize("isAuthenticated() && @UserValidator.checkIfCreatorOrAdmin(authentication,#urId)")
    @PostMapping("/{urId}/update")
    public ModelAndView doUpdateM(@PathVariable("urId") Integer urId, UserUpdateForm userUpdateForm, BindingResult bindingResult) {
        validate(userUpdateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return updateUser(urId, userUpdateForm, false);
        }
        UserDTO userDTO = modelMapper.map(userUpdateForm, UserDTO.class);
        if (userUpdateForm.getLstAuyId() != null) {
            userDTO.setLstAuyId(userUpdateForm.getLstAuyId());
        }
        userService.updateUser(userDTO);
        return updateUser(urId, userUpdateForm, true);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updateMessageStatus(@RequestBody UserUpdatePasswordForm userUpdatePasswordForm) {
        try {
            userService.updatePassword(userUpdatePasswordForm.getUrId(), userUpdatePasswordForm.getOldPassword(), userUpdatePasswordForm.getNewPassword());
            return ResponseEntity.ok().build();
        } catch (RollbackException e) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(messageUtil.getMessage(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(messageUtil.getMessage(Constants.MSG_SYSTEM_ERR));
        }
    }

    @ResponseBody
    @PostMapping(value = "/{urId}/uploadAvatar", consumes = "multipart/form-data")
    public ResponseEntity uploadFile(@PathVariable("urId") Integer urId, @RequestParam("newAvatar") MultipartFile multipartFile) {
        try {
            String url = userService.uploadAvatar(urId, multipartFile);
            return ResponseEntity.ok().body(url);
        } catch (RollbackException e) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(messageUtil.getMessage(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(messageUtil.getMessage(Constants.MSG_SYSTEM_ERR));
        }
    }

    @GetMapping("/{urId}/profile")
    public ModelAndView getProfile(@PathVariable("urId") Integer urId) {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("user/profile/profile-layout");
        Integer roleId = userService.getUserRoleByID(urId);
        modelAndView.addObject("reId", roleId);
        if (roleId.equals(Constants.ROLE_STUDENT_ID)) {
            return getStudentProfile(modelAndView, urId);
        }
        return getLecturerProfile(modelAndView, urId);
    }

    private ModelAndView getStudentProfile(ModelAndView modelAndView, Integer urId) {
        modelAndView.addObject("dataview", "user/profile/profile-student-view");
        StudentDTO studentDTO = userService.getStudentProfile(urId);
        modelAndView.addObject("user", studentDTO);
        return modelAndView;
    }

    private ModelAndView getLecturerProfile(ModelAndView modelAndView, Integer urId) {
        modelAndView.addObject("dataview", "user/profile/profile-lecturer-view");
        LecturerDTO lecturerDTO = userService.getLecturerProfile(urId);
        modelAndView.addObject("user", lecturerDTO);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/updateProfile")
    public ModelAndView updateProfile(UserProfileUpdateForm userProfileUpdateForm, boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/profile/profile-update");
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        Integer roleId = userService.getUserRoleByID(urId);
        modelAndView.addObject("reId", roleId);
        modelAndView.addObject("urId", urId);
        modelAndView.addObject("updateSuccess", updateSuccess);
        if (roleId.equals(Constants.ROLE_STUDENT_ID)) {
            return getStudentUpdate(modelAndView, urId, userProfileUpdateForm);
        }
        return getLecturerUpdate(modelAndView, urId, userProfileUpdateForm);
    }

    private ModelAndView getStudentUpdate(ModelAndView modelAndView, Integer urId, UserProfileUpdateForm userProfileUpdateForm) {
        modelAndView.addObject("dataupdate", "user/profile/profile-student-view");
        if (userProfileUpdateForm.getUrId() == null) {
            StudentDTO studentDTO = userService.getStudentProfile(urId);
            if ((studentDTO.getClassDTO() != null)) {
                studentDTO.setCsId(studentDTO.getClassDTO().getId());
            }
            userProfileUpdateForm = modelMapper.map(studentDTO,StudentProfileUpdateForm.class);
        }
        List<ClassEntity> lstClass = userService.getAllClass();
        modelAndView.addObject("lstClass", lstClass);
        modelAndView.addObject("userProfileUpdateForm", userProfileUpdateForm);
        modelAndView.addObject("updateEditor", 0);
        return modelAndView;
    }

    private ModelAndView getLecturerUpdate(ModelAndView modelAndView, Integer urId, UserProfileUpdateForm userProfileUpdateForm) {
        modelAndView.addObject("dataupdate", "user/profile/profile-lecturer-view");
        if (userProfileUpdateForm.getUrId() == null) {
            LecturerDTO lecturerDTO = userService.getLecturerProfile(urId);
            userProfileUpdateForm = modelMapper.map(lecturerDTO,LecturerProfileUpdateForm.class);
        }
        modelAndView.addObject("userProfileUpdateForm", userProfileUpdateForm);
        modelAndView.addObject("lstDegree", Constants.LST_DEGREE);
        modelAndView.addObject("updateEditor", 1);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateLecturer")
    public ModelAndView doUpdateLecturer(@ModelAttribute("userProfileUpdateForm") LecturerProfileUpdateForm lecturerProfileUpdateForm, BindingResult bindingResult) {
        validate(lecturerProfileUpdateForm,bindingResult);
        if (bindingResult.hasErrors()){
            return updateProfile(lecturerProfileUpdateForm,false);
        }
        UserDTO userDTO = modelMapper.map(lecturerProfileUpdateForm,UserDTO.class);
        LecturerDTO lecturerDTO = modelMapper.map(lecturerProfileUpdateForm,LecturerDTO.class);
        Integer urId = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        userService.updateLecturer(urId,lecturerDTO,userDTO);
        return updateProfile(lecturerProfileUpdateForm,true);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateStudent")
    public ModelAndView doUpdateStudent(@ModelAttribute("userProfileUpdateForm")StudentProfileUpdateForm studentProfileUpdateForm, BindingResult bindingResult) {
        validate(studentProfileUpdateForm,bindingResult);
        if (bindingResult.hasErrors()){
            return updateProfile(studentProfileUpdateForm,false);
        }
        UserDTO userDTO = modelMapper.map(studentProfileUpdateForm,UserDTO.class);
        StudentDTO studentDTO = modelMapper.map(studentProfileUpdateForm,StudentDTO.class);
        Integer urId = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        userService.updateStudent(urId,studentDTO,userDTO);
        return updateProfile(studentProfileUpdateForm,true);
    }
}
