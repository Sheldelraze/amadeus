package com.minh.nguyen.form.problem;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ProblemLayoutForm extends BaseForm{
    private Integer id;
    private String code;

    @Required(displayFieldName = "name")
    @MaxLength(displayFieldName = "name", maxlength = 25)
    private String name;
    private Integer viewTab;
    private Integer sutId;

    @Required(displayFieldName = "timeLimit")
    @Number(minValue=1000,maxValue=10000,displayFieldName = "timeLimit")
    private String timeLimit;

    @Required(displayFieldName = "memoryLimit")
    @Number(minValue=1,maxValue=256,displayFieldName = "memoryLimit")
    private String memoryLimit;

    private Integer difficulty;
    private Integer cntTest;
    private Integer isPublished;
    private Integer isPublic;

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
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

    public Integer getViewTab() {
        return viewTab;
    }

    public void setViewTab(Integer viewTab) {
        this.viewTab = viewTab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
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
