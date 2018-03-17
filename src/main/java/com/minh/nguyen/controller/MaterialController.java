package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.MaterialDTO;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.dto.SubjectDTO;
import com.minh.nguyen.form.material.MaterialUpdateForm;
import com.minh.nguyen.form.material.MaterialUploadForm;
import com.minh.nguyen.service.MaterialService;
import com.minh.nguyen.service.SubjectService;
import com.minh.nguyen.util.MediaTypeUtil;
import com.minh.nguyen.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.RollbackException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
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

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/all")
    public ModelAndView getAllMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("material/material-list-all");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('" + Constants.AUTH_UPLOAD_MATERIAL_TEXT + "')")
    @GetMapping("/my")
    public ModelAndView getMyMaterial() {
        ModelAndView modelAndView = createGeneralModel();
        String handle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        List<MaterialDTO> lstMaterial = materialService.getListMaterial(handle, true);
        modelAndView.addObject("lstMaterial", lstMaterial);
        modelAndView.setViewName("material/material-list-my");
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('" + Constants.AUTH_UPLOAD_MATERIAL_TEXT + "')")
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

    @PreAuthorize("hasAuthority('" + Constants.AUTH_UPLOAD_MATERIAL_TEXT + "')")
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ModelAndView doUpload(MaterialUploadForm materialUploadForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("materialUploadForm", new MaterialUploadForm());
        modelAndView.setViewName("material/material-upload");
        int mlId = 0;
        try {
            mlId = materialService.insertMaterial(materialUploadForm.getFile());
        } catch (RollbackException e) {
            modelAndView.addObject("message", new MessageVO(MessageDTO.MessageType.ERROR.toString(), e.getMessage()));
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", new MessageVO(MessageDTO.MessageType.ERROR.toString(), Constants.MSG_SYSTEM_ERR));
            return modelAndView;
        }
        return updateMaterial(mlId, null, true);
    }

    @PreAuthorize("@MaterialValidator.checkDownloadAuthority(#mlId)")
    @RequestMapping(value = "/download/{mlId}/{filename}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("mlId") Integer mlId, @PathVariable("filename") String filename) {
        try {
            String src = materialService.getMaterialLocation(mlId);
            File file = new File(src);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            MediaType mediaType = MediaTypeUtil.getMediaTypeForFileName(this.servletContext, file.getName());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentLength(file.length())
                    .contentType(mediaType)
                    .body(resource);
        } catch (RollbackException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("@MaterialValidator.checkCreator(#mlId)")
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

    @PreAuthorize("@MaterialValidator.checkCreator(#mlId)")
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
