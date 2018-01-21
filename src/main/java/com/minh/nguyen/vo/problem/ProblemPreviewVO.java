package com.minh.nguyen.vo.problem;

import com.minh.nguyen.dto.InputDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 21/01/2018
 * Purpose:
 */
public class ProblemPreviewVO {
    private String name;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String statement;
    private String input;
    private String output;
    private String note;
    private List<InputDTO> lstInput;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<InputDTO> getLstInput() {
        return lstInput;
    }

    public void setLstInput(List<InputDTO> lstInput) {
        this.lstInput = lstInput;
    }
}
