package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
public class ProblemDTO extends BaseDTO {
    private Integer id;
    private String code;
    private String name;
    private Integer viewTab;
    private Integer sutId;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer difficulty;
    private Integer cntTest;
    private Integer isPublished;
    private String statement;
    private String input;
    private String output;
    private String note;
    private String sourceCode;
    private String language;

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getViewTab() {
        return viewTab;
    }

    public void setViewTab(Integer viewTab) {
        this.viewTab = viewTab;
    }

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getCntTest() {
        return cntTest;
    }

    public void setCntTest(Integer cntTest) {
        this.cntTest = cntTest;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
