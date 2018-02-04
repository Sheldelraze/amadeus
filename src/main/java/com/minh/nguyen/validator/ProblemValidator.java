package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.exception.InputCheckException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Mr.Minh
 * @since 11/01/2018
 * Purpose:
 */
@Service("ProblemValidator")
public class ProblemValidator extends BaseValidator {

    @Autowired
    private ProblemService problemService;
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    public void validateLogic(BaseForm clazz, BindingResult errors) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(clazz,problemDTO);
        if (Objects.equals("problemSolutionScreen", clazz.getScreenName())) {
            validateUpdateSolution(problemDTO,errors);
        } else if (Objects.equals("makerAdd", clazz.getScreenName())) {

        }
    }
    public void validateUpdateSolution(ProblemDTO problemDTO,BindingResult bindingResult){
        try{
            problemService.tryCompile(problemDTO);
        }catch (CompileErrorException e){
            bindingResult.addError(new InputCheckException(
                    Constants.MSG_COMPILE_ERR + ":" + e.getMessage(),"sourceCode"));
        }
    }
    public void validateCreate(BindingResult errors){

    }
}
