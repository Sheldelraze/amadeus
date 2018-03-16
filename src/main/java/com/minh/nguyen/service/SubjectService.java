package com.minh.nguyen.service;

import com.minh.nguyen.dto.SubjectDTO;
import com.minh.nguyen.entity.SubjectEntity;
import com.minh.nguyen.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
@Service("SubjectService")
public class SubjectService extends BaseService {

    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectDTO> getAllSubject() {
        SubjectEntity subjectEntity = new SubjectEntity();
        List<SubjectEntity> lstSubject = subjectMapper.selectWithExample(subjectEntity);
        List<SubjectDTO> lst = new ArrayList<>();
        for (SubjectEntity sub : lstSubject) {
            SubjectDTO newSub = new SubjectDTO();
            newSub.setId(sub.getId());
            newSub.setName(sub.getName());
            lst.add(newSub);
        }
        return lst;
    }
}
