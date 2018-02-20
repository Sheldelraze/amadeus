package com.minh.nguyen.vo.problem;

/**
 * @author Mr.Minh
 * @since 13/01/2018
 * Purpose:
 */
public class ProblemLayoutVO {
    public static final Integer[] difficultValue = new Integer[]{1,2,3,4,5};
    private Integer id;
    private String code;
    private String name;
    private Integer viewTab;
    private Integer sutId;
    private String timeLimit;
    private String memoryLimit;
    private Integer difficulty;
    private Integer cntTest;
    private Integer isPublished;
    private Integer isPublic;
    private boolean updateSuccess;

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public static Integer[] getDifficultValue() {
        return difficultValue;
    }

    public boolean isUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(boolean updateSuccess) {
        this.updateSuccess = updateSuccess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getViewTab() {
        return viewTab;
    }

    public void setViewTab(Integer viewTab) {
        this.viewTab = viewTab;
    }

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
    }

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

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getCntTest() {
        return cntTest;
    }

    public void setCntTest(Integer cntTest) {
        this.cntTest = cntTest;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }
}
