package com.minh.nguyen.form.problem;

/**
 * @author Mr.Minh
 * @since 26/01/2018
 * Purpose:
 */
public class ProblemSubmitForm {
    private String sourceCode;
    private Integer leId;

    public Integer getLeId() {
        return leId;
    }

    public void setLeId(Integer leId) {
        this.leId = leId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
