package com.minh.nguyen.entity;

import javax.persistence.*;

/**
 * @author Mr.Minh
 * @since 26/01/2018
 * Purpose:
 */
@Table(name = "submitDetail")
public class SubmitDetailEntity extends BaseEntity {
    static final long serialVersionUID = 415L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "input")
    private String input;
    @Column(name = "output")
    private String output;
    @Column(name = "answer")
    private String answer;
    @Column(name = "result")
    private String result;
    @Column(name = "timeRun")
    private int timeRun;
    @Column(name = "memoryUsed")
    private int memoryUsed;

    public int getTimeRun() {
        return timeRun;
    }

    public void setTimeRun(int timeRun) {
        this.timeRun = timeRun;
    }

    public int getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(int memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
