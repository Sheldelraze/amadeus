package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Minh
 * @since 06/03/2018
 * Purpose:
 */
@Component("MessageValidator")
public class MessageValidator extends BaseValidator {
    @Override
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    @Override
    public void validateLogic(BaseForm clazz, BindingResult errors) {

    }

    public void validateMessage(MessageDTO messageDTO){
        if (messageDTO.getContent() != null && messageDTO.getContent().length() > Constants.MESSAGE_MAX_LENGTH){
            messageDTO.setType(MessageDTO.MessageType.FAIL);
            messageDTO.setComment("haha lêu lêu..");
        }   else{
            messageDTO.setType(MessageDTO.MessageType.SUCCESS);
        }

    }
}
