package com.minh.nguyen.form.problem;

import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 20/01/2018
 * Purpose:
 */
public class ProblemCreateTestForm extends ProblemLayoutForm {
    @Required(displayFieldName = "input")
    @MaxLength(displayFieldName = "input",maxlength = 5000000)
    private String input;
    @MaxLength(displayFieldName = "input",maxlength = 5000000)
    private String output;

    private Integer showInput;
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

    public Integer getShowInput() {
        return showInput;
    }

    public void setShowInput(Integer showInput) {
        this.showInput = showInput;
    }
}
