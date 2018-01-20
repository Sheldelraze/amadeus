package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name="input")
public class InputEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 512563421525234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

    @Column(name="showInput")
    private int showInput;

    public int getShowInput() {
        return showInput;
    }

    public void setShowInput(int showInput) {
        this.showInput = showInput;
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
}
