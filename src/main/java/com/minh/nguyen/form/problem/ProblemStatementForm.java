package com.minh.nguyen.form.problem;

import com.minh.nguyen.validator.annotation.MaxLength;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ProblemStatementForm extends ProblemLayoutForm  {
    @MaxLength(displayFieldName = "statement",maxlength = 20000)
    private String statement;
    @MaxLength(displayFieldName = "input",maxlength = 20000)
    private String input;
    @MaxLength(displayFieldName = "output",maxlength = 20000)
    private String output;
    @MaxLength(displayFieldName = "note",maxlength = 20000)
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
