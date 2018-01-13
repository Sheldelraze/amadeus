package com.minh.nguyen.form.problem;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ProblemLayoutForm extends BaseForm{
    int id;
    private String code;

    @Required(displayFieldName = "name")
    @MaxLength(displayFieldName = "name", maxlength = 25)
    private String name;
    private int viewTab;
    private int sutId;

    @Required(displayFieldName = "timeLimit")
    @Number(minValue=1000,maxValue=10000,displayFieldName = "timeLimit")
    private String timeLimit;

    @Required(displayFieldName = "memoryLimit")
    @Number(minValue=1,maxValue=256,displayFieldName = "memoryLimit")
    private String memoryLimit;

    private int difficulty;
    private int cntTest;
    private int isPublished;

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getViewTab() {
        return viewTab;
    }

    public void setViewTab(int viewTab) {
        this.viewTab = viewTab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSutId() {
        return sutId;
    }

    public void setSutId(int sutId) {
        this.sutId = sutId;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getCntTest() {
        return cntTest;
    }

    public void setCntTest(int cntTest) {
        this.cntTest = cntTest;
    }

    public int getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(int isPublished) {
        this.isPublished = isPublished;
    }
}
