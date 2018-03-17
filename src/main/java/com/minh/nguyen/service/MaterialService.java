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
import java.io.File;
import java.util.List;

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

    public List<MaterialDTO> getListMaterial(String handle, boolean getAllMaterial) {
        List<MaterialDTO> lstMaterial = materialMapper.getMaterial(handle, getAllMaterial);
        for (MaterialDTO material : lstMaterial) {
            if (!StringUtil.isNull(material.getDescription()) && material.getDescription().length() > 150) {
                material.setPreview(StringUtil.getFirstPartOfString(material.getDescription(), 150) + "...");
            }
        }
        return lstMaterial;
    }
    public int insertMaterial(MultipartFile multipartFile) {
        if (!StringUtil.isNull(multipartFile.getOriginalFilename())
                && multipartFile.getOriginalFilename().length() > Constants.MAX_FILENAME_LENGTH_SIZE) {
            rollBack(Constants.MSG_FILE_NAME_TOO_LONG_ERR);
        }
        long size = multipartFile.getSize();
        if (multipartFile.getSize() == 0) {
            rollBack(Constants.MSG_UPLOAD_ERR);
        }
        if (multipartFile.getSize() / (1024 * 1024) > Constants.MAX_UPLOAD_SIZE) {
            rollBack(Constants.MSG_UPLOAD_FILE_TOO_BIG_ERR);
        }
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        String location = StringUtil.buildString(Constants.MATERIAL_LOCATION_PREFIX, "userId-", urId.toString());
        String storedLocation = StringUtil.buildString(location, "\\", multipartFile.getOriginalFilename());
        File f = new File(storedLocation);
        if (f.exists() && !f.isDirectory()) {
            rollBack(Constants.MSG_FILENAME_EXISTED_ERR);
        }

        //add new record to database
        MaterialEntity materialEntity = new MaterialEntity();
        setCreateInfo(materialEntity);
        setUpdateInfo(materialEntity);
        materialEntity.setName(multipartFile.getOriginalFilename());
        materialEntity.setStoredLocation(storedLocation);
        materialEntity.setDownloadCnt(0);
        materialEntity.setStatus(Constants.UPLOAD_FILE_PENDING_FLAG);
        materialEntity.setIsPublic(1);
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
        if (materialEntity.getIsPublic() == null) {
            materialEntity.setIsPublic(0);
        }
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
