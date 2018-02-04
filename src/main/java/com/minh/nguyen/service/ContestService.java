package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.ContestDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.dto.TagDTO;
import com.minh.nguyen.entity.BaseEntity;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.entity.CtPmEntity;
import com.minh.nguyen.form.contest.ContestSettingForm;
import com.minh.nguyen.mapper.ContestMapper;
import com.minh.nguyen.mapper.CtPmMapper;
import com.minh.nguyen.mapper.ProblemMapper;
import com.minh.nguyen.vo.contest.ContestInformationVO;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Service
public class ContestService extends BaseService {
    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private CtPmMapper ctPmMapper;

    public int createContest(ContestDTO contestDTO) {
        ContestEntity contestEntity = new ContestEntity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contestDTO.getDate());
        stringBuilder.append(" ");
        stringBuilder.append(contestDTO.getTime());
        contestDTO.setStartTime(stringBuilder.toString());
        modelMapper.map(contestDTO, contestEntity);
        try {
            setCreateInfo(contestEntity);
            setUpdateInfo(contestEntity);
            contestEntity.setIsPublic(1);
            contestEntity.setIsPublished(0);
            contestEntity.setShowStatus(1);
            contestEntity.setCanPractice(1);
            contestEntity.setJudgeType(2);
            contestEntity.setShowTest(3);
            contestEntity.setShowSubmit(3);
            contestEntity.setShowToAll(1);
            contestMapper.insertContest(contestEntity);
            return contestEntity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ContestDTO getContestInfo(int ctId) {
        ContestDTO contestDTO = new ContestDTO();
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        modelMapper.map(contestEntity, contestDTO);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        contestDTO.setDate(dateFormat.format(contestEntity.getStartTime()));
        contestDTO.setTime(timeFormat.format(contestEntity.getStartTime()));
        return contestDTO;
    }

    public List<ProblemDTO> getProblemToAdd(int ctId){
        List<ProblemDTO> lst = problemMapper.getProblemForContest(ctId);
        for(ProblemDTO problemDTO : lst){
            StringBuilder stringBuilder = new StringBuilder();
            List<TagDTO> lstTag = problemDTO.getLstTag();
            for(int i = 0;i < lstTag.size();++i){
                stringBuilder.append(lstTag.get(i).getName());
                if (i < lstTag.size() - 1){
                    stringBuilder.append(",");
                }
            }
            problemDTO.setTag(stringBuilder.toString());
            if(Constants.BLANK.equals(stringBuilder.toString())){
                problemDTO.setTag(null);
            }
        }
        return lst;
    }
    public void setProblemHiddenStatus(Integer ctId,Integer pmId,Integer status){
        CtPmEntity ctPmEntity = new CtPmEntity();
        ctPmEntity.setCtId(ctId);
        ctPmEntity.setPmId(pmId);
        ctPmEntity.setIsHidden(status);
        setUpdateInfo(ctPmEntity);
        ctPmMapper.updateByPKExceptNullFields(ctPmEntity);
    }
    public void addProblemToContest(Integer ctId, String[] lstPmId) throws Exception{
        try {
            for (String pmId : lstPmId) {
                CtPmEntity ctPmEntity = new CtPmEntity();
                ctPmEntity.setCtId(ctId);
                ctPmEntity.setPmId(Integer.parseInt(pmId));
                ctPmEntity.setSolveCnt(0);
                ctPmEntity.setTotalSubmission(0);
                ctPmEntity.setIsHidden(0);
                setUpdateInfo(ctPmEntity);
                setCreateInfo(ctPmEntity);
                ctPmMapper.insert(ctPmEntity);
            }
        }
        catch(Exception e){
            throw e;
        }
    }

    public List<ProblemDTO> getProblemToDisplay(Integer ctId){
        List<ProblemDTO> lst = problemMapper.getProblemToDisplay(ctId);
        int cnt = 0;
        for(ProblemDTO problemDTO : lst){
            if (problemDTO.getIsHidden() == 0){
                problemDTO.setAlias(++cnt);
            }
            else{
                problemDTO.setAlias(-1);
            }
        }
        return lst;
    }
    public ContestInformationVO getInformation(int ctId){
        ContestInformationVO contestInformationVO = new ContestInformationVO();
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        modelMapper.map(contestEntity,contestInformationVO);
        Date startTime = contestEntity.getStartTime();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        contestInformationVO.setStart(sdfr.format(startTime));
        Date endTime = DateUtils.addMinutes(startTime,contestEntity.getDuration());
        contestInformationVO.setEnd(sdfr.format(endTime));
        return contestInformationVO;
    }
    public void updateContest(ContestSettingForm contestSettingForm) throws Exception{
        try {
            ContestEntity contestEntity = new ContestEntity();
            String startTime = contestSettingForm.getDate() + " " + contestSettingForm.getTime();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            modelMapper.map(contestSettingForm, contestEntity);
            contestEntity.setStartTime(dateFormat.parse(startTime));
            contestMapper.updateByPKExceptNullFields(contestEntity);
        } catch (Exception e) {
            throw e;
        }
    }

    void setCreateInfo(BaseEntity entity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setCreateClass(ContestService.class.getName());
        entity.setCreateTime(time);
        entity.setCreateUser(currentUser);
        entity.setDeleteFlg("0");
    }

    void setUpdateInfo(BaseEntity entity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setUpdateClass(ContestService.class.getName());
        entity.setUpdateTime(time);
        entity.setUpdateUser(currentUser);
    }
}
