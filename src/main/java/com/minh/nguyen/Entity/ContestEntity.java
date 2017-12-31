package com.minh.nguyen.Entity;

import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "contest")
public class ContestEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 542L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="startTime")
    private LocalDateTime startTime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name="description")
    private String description;

    @Column(name="isPublished")
    private Integer isPublished;

    @Column(name="requirement")
    private String requirement;

    @Column(name="prize")
    private String prize;

    @Column(name="showTest")
    private Integer showTest;

    @Column(name="showSubmit")
    private Integer showSubmit;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Integer getShowTest() {
        return showTest;
    }

    public void setShowTest(Integer showTest) {
        this.showTest = showTest;
    }

    public Integer getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(Integer showSubmit) {
        this.showSubmit = showSubmit;
    }
}
