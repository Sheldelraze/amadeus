package com.minh.nguyen.service;

import com.minh.nguyen.dto.ContestDTO;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.form.contest.ContestCreateForm;
import com.minh.nguyen.mapper.ContestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Service
public class ContestService extends BaseService<ContestEntity>{
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
            contestMapper.insertContest(contestEntity);
            return contestEntity.getId();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
