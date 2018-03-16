package com.minh.nguyen.service;

import com.minh.nguyen.dto.MaterialDTO;
import com.minh.nguyen.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
@Service
public class MaterialService extends BaseService {
    @Autowired
    private MaterialMapper materialMapper;

    public void insertMaterial(MaterialDTO materialDTO) {

    }
}
