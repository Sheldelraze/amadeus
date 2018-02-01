package com.minh.nguyen.service;

import com.minh.nguyen.dto.ContestDTO;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.form.contest.ContestCreateForm;
import com.minh.nguyen.mapper.ContestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Service
public class ContestService extends BaseService{
    @Autowired
    private ContestMapper contestMapper;
    public int createContest(ContestDTO contestDTO){
        ContestEntity contestEntity = new ContestEntity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contestDTO.getDate());
        stringBuilder.append(" ");
        stringBuilder.append(contestDTO.getTime());
        contestDTO.setStartTime(stringBuilder.toString());
        modelMapper.map(contestDTO,contestEntity);
        try{
            setCreateInfo(contestEntity);
            setCreateInfo(contestEntity);
            contestMapper.insertContest(contestEntity);
            return contestEntity.getId();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public ContestDTO getContestInfo(int ctId){
        ContestDTO contestDTO = new ContestDTO();
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        modelMapper.map(contestEntity,contestDTO);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        contestDTO.setDate(dateFormat.format(contestEntity.getStartTime()));
        contestDTO.setTime(timeFormat.format(contestEntity.getStartTime()));
        return contestDTO;
    }
}
