package com.minh.nguyen.vo.course;

import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 22/03/2018
 * Purpose:
 */
public class CourseSubmitVO {
    List<LanguageDTO> lstLanguage;
    List<ProblemDTO> lstProblem;
    String sourceCode;

    public List<LanguageDTO> getLstLanguage() {
        return lstLanguage;
    }

    public void setLstLanguage(List<LanguageDTO> lstLanguage) {
        this.lstLanguage = lstLanguage;
    }

    public List<ProblemDTO> getLstProblem() {
        return lstProblem;
    }

    public void setLstProblem(List<ProblemDTO> lstProblem) {
        this.lstProblem = lstProblem;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}
