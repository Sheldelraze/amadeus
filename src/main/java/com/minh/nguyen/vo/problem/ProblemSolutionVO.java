package com.minh.nguyen.vo.problem;

import com.minh.nguyen.dto.LanguageDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 14/01/2018
 * Purpose:
 */
public class ProblemSolutionVO extends  ProblemLayoutVO {
    private String sourceCode;
    private String language;
    private int leId;

    public int getLeId() {
        return leId;
    }

    public void setLeId(int leId) {
        this.leId = leId;
    }

    private List<LanguageDTO> lstLanguage;

    public List<LanguageDTO> getLstLanguage() {
        return lstLanguage;
    }

    public void setLstLanguage(List<LanguageDTO> lstLanguage) {
        this.lstLanguage = lstLanguage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
