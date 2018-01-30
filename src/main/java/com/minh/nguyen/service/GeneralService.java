package com.minh.nguyen.service;

import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.dto.SubmitDetailDTO;
import com.minh.nguyen.mapper.SubmissionMapper;
import com.minh.nguyen.util.StringUtil;
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
    public SubmissionDTO getSubmitDetail(int snId){
        List<SubmissionDTO> lstSubmit = submissionMapper.getSubmitDetail(snId);
        SubmissionDTO submit = lstSubmit.get(0);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(submit.getCreateTime());
        submit.setSubmitTime(strDate);
        List<SubmitDetailDTO> lst = submit.getLstSubmitDetail();
        for(int i = 0;i < lst.size();i++){
            SubmitDetailDTO detail = lst.get(i);
            detail.setInput(StringUtil.trimString(detail.getInput()));
            detail.setOutput(StringUtil.trimString(detail.getOutput()));
            detail.setAnswer(StringUtil.trimString(detail.getAnswer()));
            String res = detail.getResult();
            if (null != res) {
                res = res.replaceAll(" ", "&ensp;");
                detail.setResult(res);
            }
        }
        return submit;
    }
}
