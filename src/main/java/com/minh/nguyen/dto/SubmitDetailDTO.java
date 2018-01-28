package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 28/01/2018
 * Purpose:
 */
public class SubmitDetailDTO extends BaseDTO {
    private Integer id;
    private String input;
    private String output;
    private String result;
    private String answer;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
