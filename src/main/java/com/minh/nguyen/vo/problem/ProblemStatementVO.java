package com.minh.nguyen.vo.problem;

/**
 * @author Mr.Minh
 * @since 14/01/2018
 * Purpose:
 */
public class ProblemStatementVO extends  ProblemLayoutVO{
    private String statement;
    private String input;
    private String output;
    private String note;

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
}
