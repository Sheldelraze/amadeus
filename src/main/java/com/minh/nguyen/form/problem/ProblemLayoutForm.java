package com.minh.nguyen.form.problem;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ProblemLayoutForm {
    protected static int STATEMENT_VIEW = 1;
    protected static int SOLUTION_VIEW = 2;
    protected static int TEST_VIEW = 3;
    protected static int ROLE_VIEW = 4;
    private String name;
    private int sutId;
    private int timeLimit;
    private int memoryLimit;
    private int difficulty;
    private int cntTest;
    private int isPublished;

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
