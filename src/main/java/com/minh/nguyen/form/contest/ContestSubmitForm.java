package com.minh.nguyen.form.contest;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ContestSubmitForm  extends ContestLayoutForm {
    private Integer leId;
    private Integer pmId;
    private String sourceCode;

    public Integer getLeId() {
        return leId;
    }

    public void setLeId(Integer leId) {
        this.leId = leId;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
