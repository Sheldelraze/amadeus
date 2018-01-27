package com.minh.nguyen.service;

import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.mapper.SubmissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public List<SubmissionDTO> getSubmission(){
        List<SubmissionDTO> lstSubmission = submissionMapper.getSubmission();
        for(SubmissionDTO submissionDTO : lstSubmission){
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        return lstSubmission;
    }
}
