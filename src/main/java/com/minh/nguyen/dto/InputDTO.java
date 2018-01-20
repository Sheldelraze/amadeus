package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 20/01/2018
 * Purpose:
 */
public class InputDTO {
    private Integer id;
    private String input;
    private String output;
    private Integer showInput;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getShowInput() {
        return showInput;
    }

    public void setShowInput(Integer showInput) {
        this.showInput = showInput;
    }
}
