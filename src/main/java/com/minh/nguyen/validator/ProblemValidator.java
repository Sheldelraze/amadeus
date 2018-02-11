package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.exception.InputCheckException;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.mapper.AuthorityMapper;
import com.minh.nguyen.mapper.ProblemMapper;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

/**
 * @author Mr.Minh
 * @since 11/01/2018
 * Purpose:
 */
@Component("ProblemValidator")
public class ProblemValidator extends BaseValidator {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }
    public boolean checkPublic(Integer pmId) throws NoSuchPageException{

        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(pmId);
        problemEntity = problemMapper.selectByPK(problemEntity);
        if (null == problemEntity){
            throw new NoSuchPageException("Problem not found!");
        }
        if (problemEntity.getIsPublic() == 1){
            return true;
        }
        return false;
    }
    public boolean checkPermission(Authentication auth,Integer pmId,String... authority) throws NoSuchPageException{
        List<AuthorityDTO> lstAuthority = authorityMapper.getProblemAuthority(pmId, auth.getName());
        for(AuthorityDTO curAuth : lstAuthority){
            for(String requireAuth : authority){
                if (requireAuth.equals(curAuth.getName())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkOwner(Authentication auth,Integer pmId) throws NoSuchPageException{
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(pmId);
        problemEntity = problemMapper.selectByPK(problemEntity);
        if (null == problemEntity){
            throw new NoSuchPageException("Problem not found!");
        }
        if (problemEntity.getCreateUser().equals(auth.getName())){
            return true;
        }
        return false;
    }

    public void validateLogic(BaseForm clazz, BindingResult errors) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        ProblemDTO problemDTO = new ProblemDTO();
        modelMapper.map(clazz,problemDTO);
        if (Objects.equals("problemSolutionScreen", clazz.getScreenName())) {
            validateUpdateSolution(problemDTO,errors);
        }
    }
    public void validateUpdateSolution(ProblemDTO problemDTO,BindingResult bindingResult){
        try{
            problemService.tryCompile(problemDTO);
            validateSourceCode(problemDTO.getSourceCode(),bindingResult);
        }catch (CompileErrorException e){
            bindingResult.addError(new InputCheckException(
                    Constants.MSG_COMPILE_ERR,new String[]{e.getMessage()},"sourceCode"));
        }
    }

    public void validateSourceCode(String source,BindingResult bindingResult){
        final byte[] utf8Bytes;
        try {
            utf8Bytes = source.getBytes("UTF-8");
            if (utf8Bytes.length > 2048 * 256){
                bindingResult.addError(new InputCheckException(
                        Constants.MSG_FILE_TOO_LARGE_ERR,"sourceCode"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
