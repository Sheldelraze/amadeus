package com.minh.nguyen.form.problem;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 09/01/2018
 * Purpose:
 */
public class ProblemCreateForm extends BaseForm {
    private int pmId;
    @Required(displayFieldName = "code")
    @MaxLength(displayFieldName = "code", maxlength = 20)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPmId() {
        return pmId;
    }

    public void setPmId(int pmId) {
        this.pmId = pmId;
    }
}
