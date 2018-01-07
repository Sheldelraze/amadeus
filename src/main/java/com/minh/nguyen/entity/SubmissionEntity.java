package com.minh.nguyen.entity;

import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Table(name="submission")
public class SubmissionEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 5443241533L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "leId")
    private Integer leId;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "sourceCode")
    private String sourceCode;

    @Column(name = "isJudged")
    private Integer isJudged;

    @Column(name = "verdict")
    private String verdict;

    @Column(name = "isPublic")
    private Integer isPublic;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeId() {
        return leId;
    }

    public void setLeId(Integer leId) {
        this.leId = leId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getIsJudged() {
        return isJudged;
    }

    public void setIsJudged(Integer isJudged) {
        this.isJudged = isJudged;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}
