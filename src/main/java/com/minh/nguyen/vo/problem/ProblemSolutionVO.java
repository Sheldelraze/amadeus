package com.minh.nguyen.vo.problem;

/**
 * @author Mr.Minh
 * @since 14/01/2018
 * Purpose:
 */
public class ProblemSolutionVO extends  ProblemLayoutVO {
    private String sourceCode;
    private String language;

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
