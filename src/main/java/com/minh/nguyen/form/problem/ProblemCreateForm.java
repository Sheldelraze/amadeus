package com.minh.nguyen.form.problem;

import com.minh.nguyen.form.BaseForm;

/**
 * @author Mr.Minh
 * @since 09/01/2018
 * Purpose:
 */
public class ProblemCreateForm extends BaseForm {
    private int pmId;
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
