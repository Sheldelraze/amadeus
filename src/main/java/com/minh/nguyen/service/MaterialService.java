package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.MaterialDTO;
import com.minh.nguyen.entity.MaterialEntity;
import com.minh.nguyen.mapper.MaterialMapper;
import com.minh.nguyen.util.FileUtil;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
@Service
public class MaterialService extends BaseService {
    @Autowired
    private MaterialMapper materialMapper;


    @Autowired
    private HttpSession httpSession;

    public int insertMaterial(MultipartFile multipartFile) {
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        String location = StringUtil.buildString(Constants.MATERIAL_LOCATION_PREFIX, "\\userId-", urId.toString());

        //add new record to database
        MaterialEntity materialEntity = new MaterialEntity();
        setCreateInfo(materialEntity);
        setUpdateInfo(materialEntity);
        materialEntity.setName(multipartFile.getOriginalFilename());
        materialEntity.setStoredLocation(location);
        materialEntity.setDownloadCnt(0);
        int recordCnt = materialMapper.insertMaterial(materialEntity);
        if (recordCnt == 0) {
            rollBack(Constants.MSG_UPLOAD_ERR);
        }
        //asynchronously update
        uploadMaterial(multipartFile, location, materialEntity);
        return materialEntity.getId();
    }

    public MaterialDTO getMaterialInfo(Integer mlId) {
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(mlId);
        materialEntity = materialMapper.selectByPK(materialEntity);
        return modelMapper.map(materialEntity, MaterialDTO.class);
    }

    public void updateMaterial(MaterialDTO material) {
        MaterialEntity materialEntity = modelMapper.map(material, MaterialEntity.class);
        int recordCnt = materialMapper.updateNotNullByPK(materialEntity);
        if (recordCnt == 0) {
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }

    @Async
    public void uploadMaterial(MultipartFile multipartFile, String location, MaterialEntity materialEntity) {
        //do upload material
        try {
            FileUtil.store(multipartFile, location);
            materialEntity.setStatus(Constants.UPLOAD_FILE_SUCCESS_FLAG);
            materialMapper.updateNotNullByPK(materialEntity);
        } catch (Exception e) {
            e.printStackTrace();
            materialEntity.setStatus(Constants.UPLOAD_FILE_FAIL_FLAG);
            materialMapper.updateNotNullByPK(materialEntity);
        }
    }
}
