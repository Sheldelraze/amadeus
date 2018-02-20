package com.minh.nguyen.dto;

import java.util.Date;
import java.util.List;

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
    private Integer isPublic;
    private String statement;
    private String input;
    private String output;
    private Integer showInput;
    private String note;
    private String sourceCode;
    private Integer leId;
    private List<InputDTO> lstInput;
    private List<TagDTO> lstTag;
    private String tag;
    private Integer isHidden;
    private Integer solveCnt;
    private Integer alias;
    private Integer isSolved;
    private Integer isFirstSolve;
    private Integer submitCnt;
    private Integer totalSubmission;
    private String solvePercentage;
    private String solveTime;
    private UserDTO creator;
    private List<SubmissionDTO> lstSubmission;
    private Date firstSolveTime;

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getSolvePercentage() {
        return solvePercentage;
    }

    public void setSolvePercentage(String solvePercentage) {
        this.solvePercentage = solvePercentage;
    }

    public Integer getTotalSubmission() {
        return totalSubmission;
    }

    public void setTotalSubmission(Integer totalSubmission) {
        this.totalSubmission = totalSubmission;
    }

    public Date getFirstSolveTime() {
        return firstSolveTime;
    }

    public void setFirstSolveTime(Date firstSolveTime) {
        this.firstSolveTime = firstSolveTime;
    }

    public Integer getIsFirstSolve() {
        return isFirstSolve;
    }

    public void setIsFirstSolve(Integer isFirstSolve) {
        this.isFirstSolve = isFirstSolve;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public Integer getSubmitCnt() {
        return submitCnt;
    }

    public void setSubmitCnt(Integer submitCnt) {
        this.submitCnt = submitCnt;
    }

    public Integer getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(Integer isSolved) {
        this.isSolved = isSolved;
    }

    public List<SubmissionDTO> getLstSubmission() {
        return lstSubmission;
    }

    public void setLstSubmission(List<SubmissionDTO> lstSubmission) {
        this.lstSubmission = lstSubmission;
    }

    public Integer getAlias() {
        return alias;
    }

    public void setAlias(Integer alias) {
        this.alias = alias;
    }

    public Integer getSolveCnt() {
        return solveCnt;
    }

    public void setSolveCnt(Integer solveCnt) {
        this.solveCnt = solveCnt;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getLeId() {
        return leId;
    }

    public void setLeId(Integer leId) {
        this.leId = leId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<TagDTO> getLstTag() {
        return lstTag;
    }

    public void setLstTag(List<TagDTO> lstTag) {
        this.lstTag = lstTag;
    }

    public List<InputDTO> getLstInput() {
        return lstInput;
    }

    public void setLstInput(List<InputDTO> lstInput) {
        this.lstInput = lstInput;
    }

    public Integer getShowInput() {
        return showInput;
    }

    public void setShowInput(Integer showInput) {
        this.showInput = showInput;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
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
