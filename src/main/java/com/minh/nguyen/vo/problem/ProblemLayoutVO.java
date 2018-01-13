package com.minh.nguyen.vo.problem;

/**
 * @author Mr.Minh
 * @since 13/01/2018
 * Purpose:
 */
public class ProblemLayoutVO {
    public static final int[] difficultValue = new int[]{1,2,3,4,5};
    int id;
    private String code;
    private String name;
    private int viewTab;
    private int sutId;
    private int timeLimit;
    private int memoryLimit;
    private int difficulty;
    private int cntTest;
    private int isPublished;
    private boolean updateSuccess;

    public static int[] getDifficultValue() {
        return difficultValue;
    }

    public boolean isUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(boolean updateSuccess) {
        this.updateSuccess = updateSuccess;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewTab() {
        return viewTab;
    }

    public void setViewTab(int viewTab) {
        this.viewTab = viewTab;
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
