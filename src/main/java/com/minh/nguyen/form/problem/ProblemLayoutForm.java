package com.minh.nguyen.form.problem;

import com.minh.nguyen.form.BaseForm;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ProblemLayoutForm extends BaseForm{
    private String name;
    private int viewTab;
    private int sutId;
    private int timeLimit;
    private int memoryLimit;
    private int difficulty;
    private int cntTest;
    private int isPublished;

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

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
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
