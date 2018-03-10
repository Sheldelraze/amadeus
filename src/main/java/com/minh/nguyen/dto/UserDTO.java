package com.minh.nguyen.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
public class UserDTO extends BaseDTO{
    private Integer id;
    private String handle;
    private String password;
    private String fullname;
    private String phoneNumber;
    private String emailAddress;
    private String dateOfBirth;
    private String avatar;
    private String description;
    private Integer loginAttempt;
    private Integer isOnline;
    private Integer isActived;
    private Integer isLocked;
    private Date lastLogin;
    private Date expiredDate;
    private Integer accountType;
    private RoleDTO role;
    private Integer score;
    private Integer penalty;
    private String authorityName;
    private Date contestStartTime;
    private List<ProblemDTO> lstProblem;
    private List<AuthorityDTO> lstAuthority;
    private ConversationDTO conversation;

    public ConversationDTO getConversation() {
        return conversation;
    }

    public void setConversation(ConversationDTO conversation) {
        this.conversation = conversation;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Date getContestStartTime() {
        return contestStartTime;
    }

    public void setContestStartTime(Date contestStartTime) {
        this.contestStartTime = contestStartTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public List<ProblemDTO> getLstProblem() {
        return lstProblem;
    }

    public void setLstProblem(List<ProblemDTO> lstProblem) {
        this.lstProblem = lstProblem;
    }

    public List<AuthorityDTO> getLstAuthority() {
        return lstAuthority;
    }

    public void setLstAuthority(List<AuthorityDTO> lstAuthority) {
        this.lstAuthority = lstAuthority;
    }

    public RoleDTO getRoleDTO() {
        return role;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.role = roleDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
