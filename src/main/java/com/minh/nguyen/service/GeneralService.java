package com.minh.nguyen.service;

import com.minh.nguyen.dto.ContestDTO;
import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.dto.SubmitDetailDTO;
import com.minh.nguyen.entity.BaseEntity;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.mapper.ContestMapper;
import com.minh.nguyen.mapper.SubmissionMapper;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 27/01/2018
 * Purpose:
 */
@Component("GeneralService")
public class GeneralService extends BaseService {

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private ContestMapper contestMapper;

    public List<SubmissionDTO> getSubmission(){
        List<SubmissionDTO> lstSubmission = submissionMapper.getSubmission();
        for(SubmissionDTO submissionDTO : lstSubmission){
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        return lstSubmission;
    }
    public SubmissionDTO getSubmitDetail(int snId){
        List<SubmissionDTO> lstSubmit = submissionMapper.getSubmitDetail(snId);
        SubmissionDTO submit = lstSubmit.get(0);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(submit.getCreateTime());
        submit.setSubmitTime(strDate);
        List<SubmitDetailDTO> lst = submit.getLstSubmitDetail();
        for(int i = 0;i < lst.size();i++){
            SubmitDetailDTO detail = lst.get(i);
            detail.setInput(detail.getInput());
            detail.setOutput(detail.getOutput());
            detail.setAnswer(detail.getAnswer());
            String res = detail.getResult();
            if (null != res) {
                res = res.replaceAll(" ", "&ensp;");
                detail.setResult(res);
            }
        }
        return submit;
    }
    public SubmissionDTO getSubmitDetail(int snId,int ctId){
        SubmissionDTO submissionDTO = getSubmitDetail(snId);
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setId(ctId);
        contestDTO.setName(contestEntity.getName());
        submissionDTO.setContestDTO(contestDTO);
        return submissionDTO;
    }
    void setCreateInfo(BaseEntity entity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setCreateClass(GeneralService.class.getName());
        entity.setCreateTime(time);
        entity.setCreateUser(currentUser);
        entity.setDeleteFlg("0");
    }

    void setUpdateInfo(BaseEntity entity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setUpdateClass(GeneralService.class.getName());
        entity.setUpdateTime(time);
        entity.setUpdateUser(currentUser);
    }
}
